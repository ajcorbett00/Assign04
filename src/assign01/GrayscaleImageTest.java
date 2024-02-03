package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage largeImage;

    @BeforeEach
    void setUp() {
        largeImage = new GrayscaleImage(new double[][]{{1,2,3,4,5,6,7},{2,3,4,5,6,7,8},{3,4,5,6,7,8,9}});
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
    }

    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);
    }
    @Test
    void testGetPixelException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            largeImage.getPixel(10,10);
        });
        String expectedMessage = "X and or Y are out of bounds";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);
    }

    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
    }
    @Test
    void averageBirghtnessLarge(){
        assertEquals(largeImage.averageBrightness(), 5, 5*.001);

    }
    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
    }
    @Test
    void normalizeBiggerImage() {
        var largeNorm = largeImage.normalized();
        assertEquals(largeNorm.averageBrightness(), 127, 127*.001);
    }
    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        var mirrored = smallSquare.mirrored();
        assertEquals(mirrored.getPixel(0,0), expected.getPixel(0,0));
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));

    }
    @Test
    void croppedNegativeTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            largeImage.cropped(-1,1,2,2);
        });
        String expectedMessage = "inputs are outside the image";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testCropppedException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            largeImage.cropped(1,0,4,4);
        });
        String expectedMessage = "inputs are outside the image";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
    }
}