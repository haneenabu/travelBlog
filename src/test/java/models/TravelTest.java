package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/9/17.
 */
public class TravelTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewTestObjectGetsCorrectlyCreated_true() throws Exception {
        Travel testTravel = setupNewTravel();
        assertEquals(true, testTravel instanceof Travel);
    }
    @Test
    public void InstantiatesWithContent_true() throws Exception {
        Travel testTravel = setupNewTravel();
        assertEquals("Brazil", testTravel.getTitle());
    }
    @Test
    public void AllTravelContainsSelectedInstances_true() throws Exception {
        Travel testTravel = setupNewTravel();
        Travel secondTravel = new Travel("Spain", "Spring 2018");
        assertTrue(Travel.getAll().contains(testTravel));
        assertTrue(Travel.getAll().contains(secondTravel));
    }
    //helper methods
    public Travel setupNewTravel(){
        return new Travel ("Brazil", "Summer 2017");
    }
}