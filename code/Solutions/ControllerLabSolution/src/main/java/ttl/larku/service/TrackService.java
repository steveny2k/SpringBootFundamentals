package ttl.larku.service;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Track;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TrackService {

    private BaseDAO<Track> trackDAO;

    public TrackService(BaseDAO<Track> trackDAO) {
        this.trackDAO = trackDAO;
    }

    public Track createTrack(String title) {
        Track track = Track.title(title).build();
        track = trackDAO.create(track);

        return track;
    }

    public Track createTrack(String title, String artist, String album, String duration, String date) {
        return Track.title(title).artist(artist).album(album).duration(duration).date(date).build();
    }


    public Track createTrack(Track track) {
        track = trackDAO.create(track);

        return track;
    }

    public void deleteTrack(int id) {
        Track track = trackDAO.get(id);
        if (track != null) {
            trackDAO.delete(track);
        }
    }

    public void updateTrack(Track track) {
        trackDAO.update(track);
    }

    public List<Track> getTracksByTitle(String title) {
        String lc = title.toLowerCase();
        Predicate<Track> pred = (t) -> t.getTitle().toLowerCase().contains(lc);
        List<Track> found = getTracksBy(pred);

        return found;
    }

    /**
     * We use Predicate to create a general query mechanism
     *
     * @param pred
     * @return
     */
    public List<Track> getTracksBy(Predicate<Track> pred) {
        List<Track> found = trackDAO.findBy(pred);

        return found;
    }

    public Track getTrack(int id) {
        return trackDAO.get(id);
    }

    public List<Track> getAllTracks() {
        return trackDAO.getAll();
    }

    public BaseDAO<Track> getTrackDAO() {
        return trackDAO;
    }

    public void setTrackDAO(BaseDAO<Track> trackDAO) {
        this.trackDAO = trackDAO;
    }

    public void clear() {
        trackDAO.deleteStore();
        trackDAO.createStore();
    }


    public void updateTrackPartial(int id, Map<String, Object> props) {
        Track track = trackDAO.get(id);
        if (track != null) {
            Class<?> clazz = Track.class;
            Map<String, Method> methods = Arrays
                    .asList(clazz.getMethods()).stream()
                    .filter(m -> m.getName().startsWith("set"))
                    .collect(Collectors.toMap(m -> m.getName(), m -> m));

            props.forEach((name, value) -> {
                if (!name.equals("id")) {
                    String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = methods.get(setMethodName);
                    if (m != null) {
                        Class<?>[] paramTypes = m.getParameterTypes();
                        if (paramTypes.length == 1) {
                            Object param = value;
                            try {
                                Class<?> pClass = paramTypes[0];
                                //We only handle String properties for now.
                                if (pClass.equals(String.class)) {
                                    param = String.valueOf(value);
                                }
                                m.invoke(track, param);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public void updateTrackPartialBeanWrapper(int id, Map<String, Object> props) {
        Track track = trackDAO.get(id);
        if (track != null) {

            BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(track);
            props.forEach((name, value) -> {
                if (!name.equals("id")) {
                    if (bw.isWritableProperty(name)) {
                        Class<?> pClass = bw.getPropertyType(name);
                        bw.setPropertyValue(name, bw.convertIfNecessary(value, pClass));
                    }
                }
            });
        }
    }

}
