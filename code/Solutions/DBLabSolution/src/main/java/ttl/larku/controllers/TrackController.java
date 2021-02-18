package ttl.larku.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Track;
import ttl.larku.service.TrackService;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping(value = "/track")
public class TrackController {

    @Resource(name = "trackService")
    private TrackService trackService;

    /**
     * A very simple example of passing in query parameters to specify search criteria.
     * Should be called like: http://localhost:8080/track?q=title=Blah%20Blah%20Blah.
     * We are only dealing with title and artist right now, and match using a
     * String.contains rather than String.equals.
     * @param query
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllTracks(@RequestParam(name="q", required=false) String query) {
        Predicate<Track> pred = (t) -> true;
        if(query != null) {
            String [] q = query.split("=");
            switch(q[0]) {
                case "title":
                    pred = (t) -> t.getTitle().toUpperCase().contains(q[1].toUpperCase());
                    break;
                case "artist":
                    pred = (t) -> t.getArtist().toUpperCase().contains(q[1].toUpperCase());
                    break;
            };
        }
//        List<Track> tracks = trackService.getAllTracks();
        List<Track> tracks = trackService.getTracksBy(pred);
        return ResponseEntity.ok(tracks);
    }

    /**
     * POST is allowed to do anything.
     * An example of using a POST for a query.  We need
     * POST because Spring MVC does not allow a body in a
     * GET request.
     * Here we are doing a simple version of query by example.
     * The user sends us a Track as json with the query fields
     * filled in, everything else null.
     *
     * @param example
     * @return
     */
    @PostMapping("/query")
    public ResponseEntity<?> getAllTracksWithBody(@RequestBody Track example) {
        Predicate<Track> pred = null;
        //We are going to 'or' all the fields in the example object.
        if(example.getTitle() != null) {
            Predicate<Track> titlePred = (t) -> t.getTitle().toUpperCase().contains(example.getTitle().toUpperCase());
            pred =  pred == null ? titlePred : pred.or(titlePred);
        }

        if(example.getArtist() != null) {
            Predicate<Track> titlePred = (t) -> t.getArtist().toUpperCase().contains(example.getArtist().toUpperCase());
            pred =  pred == null ? titlePred : pred.or(titlePred);
        }
        //etc. etc.

        //If nothing yet, then return all.
        pred = pred == null ? (t) -> true : pred;

        List<Track> tracks = trackService.getTracksBy(pred);
        return ResponseEntity.ok(tracks);
    }

    /**
     * Numbers will come here
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<?> getTrack(@PathVariable("id") int id) {
        Track track = trackService.getTrack(id);
        if (track == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(track);
    }

    /**
     * Starting with alpha will come in here
     * @param otherStuff
     * @return
     */
    @GetMapping("/{otherstuff:[a-z].*}")
    public ResponseEntity<?> getOtherStuff(@PathVariable("otherstuff") String otherStuff) {
        return ResponseEntity.ok(otherStuff);
    }

//    @GetMapping("/title/{title}")
//    public ResponseEntity<?> getTrack(@PathVariable("title") String title) {
//        List<Track> tracks = trackService.getTracksByTitle(title);
//        return ResponseEntity.ok(tracks);
//    }


    @PostMapping
    public ResponseEntity<?> addTrack(@RequestBody Track track) {
        Track newTrack = trackService.createTrack(track);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTrack.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newTrack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) {
        Track track = trackService.getTrack(id);
        if (track == null) {
            return ResponseEntity.badRequest().build();
        }
        trackService.deleteTrack(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateTrack(@RequestBody Track track) {
        trackService.updateTrack(track);
        return ResponseEntity.noContent().build();
    }
}
