package renderer;

import org.junit.jupiter.api.Test;
import primitives .*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testWriteGrid() {

        ImageWriter picture=new ImageWriter("grid_picture3" ,800,500);
        for(int i=0;i<800;i++)
        {
            for(int j=0;j<500;j++)
            {
                if(i%50==0 || j%50==0)
                {
                    picture.writePixel(i, j, new Color(172,206,250) );
                }
                else
                {
                   // picture.writePixel(i, j, new Color(230,250,102));
                    j+=49;
                }

            }
        }
        picture.writeToImage();





    }


}