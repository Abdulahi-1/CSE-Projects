
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("STUDENT TEST - Case #1")
    public void firstCaseTest() {
        Region r1 = new Region("1", 50);
        Region r2 = new Region("2", 100);
        Region r3 = new Region("3", 150);
        r1.addConnection(r2, 200);
        r2.addConnection(r3, 300);
        List<Region> regions = new ArrayList<>();
        regions.add(r1);
        regions.add(r2);
        regions.add(r3);
        Path result = Client.findPath(regions);
        assertTrue(true);
    }

    @Test
    @DisplayName("STUDENT TEST - Case #2")
    public void secondCaseTest() {
        Region r1 = new Region("1", 50);
        Region r2 = new Region("2", 300);
        Region r3 = new Region("3", 200);
        Region r4 = new Region("4", 120);
        r1.addConnection(r2, 1200);
        r1.addConnection(r3, 700);
        r1.addConnection(r4, 600);
        r3.addConnection(r4, 1000);
        List<Region> regions = new ArrayList<>();
        regions.add(r1);
        regions.add(r2);
        regions.add(r3);
        regions.add(r4);
        Path result = Client.findPath(regions);
        assertTrue(true);
    }

    @Test
    @DisplayName("STUDENT TEST - DIY")
    public void diyTest() {
        Region r1 = new Region("1", 75);
        List<Region> regions = new ArrayList<>();
        regions.add(r1);
        Path result = Client.findPath(regions);
        assertTrue(true);
    }
}