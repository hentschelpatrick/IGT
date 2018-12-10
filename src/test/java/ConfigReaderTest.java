import de.igt.tools.Config;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfigReaderTest {

    @Test
    public void testA_readConfigFile() {
        int numberOfCustomers = Config.NUMBER_OF_CUSTOMERS;
        assertEquals(10, numberOfCustomers, 0.0001);

        int numberOfAirports = Config.NUMBER_OF_AIRPOTS;
        assertEquals(100, numberOfAirports, 0.0001);

        int numberOfFlights = Config.NUMBER_OF_FLIGHTS;
        assertEquals(100, numberOfFlights, 0.0001);

        int numberOfFlightSegments = Config.NUMBER_OF_FLIGHTSEGMENTS;
        assertEquals(100, numberOfFlightSegments, 0.0001);
    }

}
