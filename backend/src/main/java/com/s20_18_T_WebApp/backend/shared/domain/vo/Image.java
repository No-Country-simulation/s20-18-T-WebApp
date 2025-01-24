package com.s20_18_T_WebApp.backend.shared.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Embeddable
public class Image {

    @Column(name = "image_url", length = 500)
    private String url;

    protected Image() {
    }

    private Image(String url) {this.url = Objects.requireNonNull(url, "Original url cannot be null.");}

    /**
     * @return the URL of the image that is most suitable for display in a
     * thumbnail context.
     */
    public String getThumbnailUrl() {
        return adjustForLowSize(url);
    }

    /**
     * @return the public id of the image that can be used to identify
     * the image in Cloudinary.
     */
    public String extractPublicId() {
      // Extract the public id from the image URL
      // The public id is the part of the URL after the last slash
      // and before the first question mark.
      return url.substring(url.lastIndexOf("/") + 1)
              .split("\\?")[0];
    }

    /**
     * @param e the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object e) {
        if (this == e) return true;
        if (e == null || getClass() != e.getClass()) return false;
        Image image = (Image) e;
        return Objects.equals(url, image.url);
    }

    /**
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        // The hash code is based on the URL of the image
        return Objects.hash(url);
    }
    /**
     * Adjusts the given URL to be suitable for display in a low-size
     * context, such as a thumbnail.
     * @param secureUrl the image URL to adjust
     * @return the adjusted URL
     */
    private static String adjustForLowSize(String secureUrl) {
        if (secureUrl == null) {
            return null;
        }
        // The adjustment is to add a transformation to the URL, which
        // will be used to generate a thumbnail of the image.
        // The transformation is to resize the image to a width of 400
        // pixels, while maintaining the aspect ratio.
        // The image will be scaled down to fit within a 400x400 box.
        // The dpr_auto parameter is used to automatically adjust the
        // image based on the device's pixel density.
        // The f_auto parameter is used to automatically select the
        // best format for the image.
        // The q_auto parameter is used to automatically select the best
        // quality for the image.
        // The c_scale parameter is used to scale the image down to fit
        // within the specified width and height.
        return secureUrl.replace("/upload/", "/upload/w_400,c_scale,q_auto,f_auto,dpr_auto/");
    }
}
