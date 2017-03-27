package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Arrival;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Test the StopManager
 */
public class TestStopManager {

    @Before
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testBoring() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        Stop r = StopManager.getInstance().getStopWithId(9999);
        assertEquals(1, StopManager.getInstance().getNumStops());
        assertEquals("My house", s9999.getName());
        StopManager.getInstance().getStopWithId(9998);
        assertEquals(2, StopManager.getInstance().getNumStops());
        assertEquals(s9999, StopManager.getInstance().getStopWithId(9999));
        assertEquals(s9999, StopManager.getInstance().getStopWithId(9999, "My house", new LatLon(-49.2, 123.2)));
        assertEquals(2, StopManager.getInstance().getNumStops());
        assertEquals(s9999, r);
    }

    @Test
    public void testStop() {
        LatLon latlon = new LatLon(-49.2, 123.2);
        LatLon latlon1 = new LatLon(49.2, 123.2);
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        assertEquals("My house", s9999.getName());
        assertEquals(9999, s9999.getNumber());
        assertEquals(latlon, s9999.getLocn());
        Route r43 = new Route("43");
        Route r44 = new Route("44");
        Set<Route> routes;
        routes = new HashSet<>();
        routes.add(r43);
        s9999.addRoute(r43);
        assertEquals(routes, s9999.getRoutes());
        routes.add(r44);
        s9999.addRoute(r44);
        assertEquals(routes, s9999.getRoutes());
        Arrival a = new Arrival(23, "Home", r43);
        s9999.addArrival(a);
        s9999.setName("Bad Home");
        assertEquals("Bad Home", s9999.getName());
        s9999.setLocn(new LatLon(49.2, 123.2));
        assertEquals(latlon1, s9999.getLocn());
        assertTrue(s9999.onRoute(r43));
        assertTrue(s9999.onRoute(r44));
        s9999.removeRoute(r43);
        assertTrue(s9999.onRoute(r44));
        assertFalse(s9999.onRoute(r43));
        s9999.clearArrivals();
    }
}
