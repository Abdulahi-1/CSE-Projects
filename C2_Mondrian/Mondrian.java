
import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

// This class generates digital art inspired by Piet Mondrian's painting style.
// It creates abstract paintings with distinct colored rectangles.
public class Mondrian {
    private static final Random random = new Random();
    private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE};

    // Behavior: 
    //   - This method creates a basic Mondrian-style composition on a canvas.
    // Parameters:
    //   - pixels: the canvas where the colors are applied
    // Returns:
    //   - N/A
    // Exceptions:
    //   - If the given pixels is null, an IllegalArgumentException is thrown.
    //   - If the height and width of the canvas is lower than 300, an
    //  IllegalArgumentException is thrown.
    public void paintBasicMondrian(Color[][] pixels) {
        if (pixels == null) {
            throw new IllegalArgumentException();
        }
        int height = pixels.length;
        int width = pixels[0].length;
        if (height < 300 || width < 300) {
            throw new IllegalArgumentException();
        }
        paintBasicMondrianHelper(pixels, 0, 0, width, height);
    }

    // Behavior: 
    //   - This method divides the canvas into sections and applies colors to form a basic
    //  Mondrian-style composition.
    // Parameters:
    //   - pixels: the canvas where the colors are applied
    //   - x1: the starting x-coordinate of the region
    //   - y1: the starting y-coordinate of the region
    //   - width: the width of the region
    //   - height: the height of the region
    // Returns:
    //   - N/A
    // Exceptions:
    //   - N/A
    private void paintBasicMondrianHelper(Color[][] pixels, int x1, int y1,
     int width, int height){
        if (!(width >= pixels[0].length / 4) && !(height >= pixels.length / 4)) {
            fill(pixels, x1, y1, x1 + width, y1 + height);
        }
        if (width >= pixels[0].length / 4 && height >= pixels.length / 4) {
            int horizontal =random.nextInt(Math.max(1, width - 20)) + x1 + 10;
            int vertical = random.nextInt(Math.max(1, height - 20)) + y1 + 10;
            paintBasicMondrianHelper(pixels, x1, y1, horizontal - x1, vertical - y1);
            paintBasicMondrianHelper(pixels, horizontal, y1, x1 + width - horizontal, 
            vertical - y1);
            paintBasicMondrianHelper(pixels, x1, vertical, horizontal - x1, 
            y1 + height - vertical);
            paintBasicMondrianHelper(pixels, horizontal, vertical,
            x1 + width - horizontal, y1 + height - vertical);
        } 
        else if (pixels[0].length / 4 <= width) {
            int horizontal = random.nextInt(Math.max(1, width - 20)) + x1 + 10;
            paintBasicMondrianHelper(pixels, x1, y1, horizontal - x1, height);
            paintBasicMondrianHelper(pixels, horizontal, y1, x1 + width - horizontal, height);
        } 
        else if (pixels.length / 4 <= height) {
            int vertical = random.nextInt(Math.max(1, height - 20)) + y1 + 10;
            paintBasicMondrianHelper(pixels, x1, y1, width, vertical - y1);
            paintBasicMondrianHelper(pixels, x1, vertical, width, y1 + height - vertical);
        }
    }

    // Behavior: 
    //   - This method creates a more complex Mondrian-style compostion on a canvas.
    // Parameters:
    //   - pixels: the canvas where the colors are applied
    // Returns:
    //   - N/A
    // Exceptions:
    //   - If the given pixels is null, an IllegalArgumentException is thrown.
    public void paintComplexMondrian(Color[][] pixels) {
        if (pixels == null) {
            throw new IllegalArgumentException();
        }
        int height = pixels.length;
        int width = pixels[0].length;
        if (height < 300 || width < 300) {
            throw new IllegalArgumentException();
        }
        paintComplexMondrianHelper(pixels, 0, 0, width, height);
    }

    // Behavior: 
    //   - This method divides the canvas into sections and applies colors to form a complex
    //  Mondrian-style composition with multiple splits.
    // Parameters:
    //   - pixels: the canvas where the colors are applied
    //   - x1: the starting x-coordinate of the region
    //   - y1: the starting y-coordinate of the region
    //   - width: the width of the region
    //   - height: the height of the region
    // Returns:
    //   - N/A
    // Exceptions:
    //   - N/A
    private void paintComplexMondrianHelper(Color[][] pixels, int x1, int y1,
    int width, int height) {
        if (width < pixels[0].length / 4 || height < pixels.length / 4) {
            fill(pixels, x1, y1, x1 + width, y1 + height);
        }
        else {
            int verticalSplits = Math.max(1, 1 + random.nextInt(4));
            int horizontalSplits = Math.max(1, 1 + random.nextInt(4));
            List<Integer> verticalLines = new ArrayList<>();
            List<Integer> horizontalLines = new ArrayList<>();
            for (int i = 1; i < verticalSplits; i++) {
                if (width > 20) {
                    verticalLines.add(random.nextInt(width - 20) + x1 + 10);
                }
            }
            for (int i = 1; i < horizontalSplits; i++) {
                if (height > 20) {
                    horizontalLines.add(random.nextInt(height - 20) + y1 + 10);
                }
            }
            verticalLines.add(x1 + width);
            horizontalLines.add(y1 + height);
            int prevX = x1;
            for (int currX : verticalLines) {
                int prevY = y1;
                for (int currY : horizontalLines) {
                    paintComplexMondrianHelper(pixels, prevX, prevY, currX - prevX, currY
                     - prevY);
                    prevY = currY;
                }
                prevX = currX;
            }
        }
    }


    // Behavior: 
    //   - This method fills a specified region with a random color, leaving a black border.
    // Parameters:
    //   - pixels: the canvas where the colors are applied
    //   - x1: the starting x-coordinate of the region
    //   - y1: the starting y-coordinate of the region
    //   - x2: the ending x-coordinate of the region
    //   - y2: the ending y-coordinate of the region
    //   - width: the width of the region
    //   - height: the height of the region
    // Returns:
    //   - N/A
    // Exceptions:
    //   - N/A
    private static void fill(Color[][] pixels, int x1, int y1, int x2, int y2) {
        Color randomColor = COLORS[random.nextInt(4)];
        for (int i = x1 + 1; i < x2 - 1; i++) {
            for (int j = y1 + 1; j < y2 - 1; j++) {
                pixels[j][i] = randomColor;
            }
        }
    }
}
