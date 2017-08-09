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
        Travel.clearAllTravel();
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
    @Test
    public void ClearsAllTravel() throws Exception{
        Travel testTravel = setupNewTravel();
        Travel secondTravel = new Travel("Belize","Summer 2012");
        Travel.clearAllTravel();
        assertFalse(Travel.getAll().contains(testTravel));
        assertFalse(Travel.getAll().contains(secondTravel));
    }
    @Test
    public void travelInstantiateWithId() throws Exception {
        Travel testTravel = setupNewTravel();
        assertEquals(1, testTravel.getId());
    }
    @Test
    public void travelInstantiateWithMultipleEntries() throws Exception {
        Travel testTravel = setupNewTravel();
        Travel secondTravel = new Travel("Istanbul", "December 2016");
        assertEquals(1, testTravel.getId());
        assertEquals(2, secondTravel.getId());
    }
    @Test
    public void findBySpecificId() throws Exception{
        Travel testTravel = setupNewTravel();
        Travel secondTravel = new Travel("Istanbul", "December 2016");
        assertEquals(2, Travel.findById(secondTravel.getId()).getId());
        assertEquals("Istanbul", Travel.findById(secondTravel.getId()).getTitle());
    }

    @Test
    public void deleteBySpecificId() throws Exception{
        Travel testTravel = setupNewTravel();
        Travel secondTravel = new Travel("Istanbul", "December 2016");
        testTravel.deleteByID(1);
        assertEquals(1, Travel.getAll().size());
        assertEquals("Istanbul", Travel.findById(secondTravel.getId()).getTitle());
    }
    //helper methods
    public Travel setupNewTravel(){
        return new Travel ("Brazil", "Summer 2017");
    }
}