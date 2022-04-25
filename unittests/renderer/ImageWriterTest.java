package renderer;

import org.junit.jupiter.api.Test;

class ImageWriterTest {

    @Test
    void writeToImage() {
        Color colorGrid = new Color(100, 100, 100);
        Color colorBackground = new Color(255, 255, 0);
        ImageWriter imageWriter = new ImageWriter("My picture", 800, 500);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nX; ++i)
            for (int j = 0; j < nY; ++j) {
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(i, j, colorGrid);
                else
                    imageWriter.writePixel(i, j, colorBackground);
            }

        imageWriter.writeToImage();
    }
}