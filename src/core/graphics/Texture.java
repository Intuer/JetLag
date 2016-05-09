package core.graphics;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_BGR;
import static org.lwjgl.opengl.GL12.GL_BGRA;
import static org.lwjgl.opengl.GL13.GL_COMPRESSED_RGBA;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;

/**
 * Created by Nicklas Hersén on 2016-05-08.
 */
public class Texture {
    private int texture_unit;
    private int texture_handle;
    private ByteBuffer image_data;
    private int width, height;

    public Texture(String imgName) {
        this(imgName, GL_TEXTURE0);
    }

    public Texture(String imgName, int unit) {
        boolean has_alpha;

        texture_unit = unit;
        texture_handle = glGenTextures();

        File file = new File(imgName);
        BufferedImage img;

        try {
            img = ImageIO.read(file);
        } catch (Exception e) { throw new RuntimeException("Invalid image path."); }

        width = img.getWidth();
        height = img.getHeight();

        byte raster[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        image_data = BufferUtils.createByteBuffer(raster.length);
        image_data.put(raster).flip();

        bind();

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        has_alpha = img.getColorModel().hasAlpha();

        if (has_alpha) {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_BGRA, GL_UNSIGNED_BYTE, image_data);
        } else {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_BGR, GL_UNSIGNED_BYTE, image_data);
        }

        unbind();
    }

    public void destroy() {
        glDeleteTextures(texture_handle);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texture_handle);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
