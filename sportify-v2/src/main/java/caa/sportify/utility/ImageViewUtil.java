package caa.sportify.utility;

import javafx.scene.image.ImageView;

/**
 * @author Crispin A.
 *
 */
public class ImageViewUtil {

	/**
	 * 
	 * Utility method that resizes the image to have a specific height while
	 * preserving the ratio and using higher quality filtering method; this
	 * ImageView is also cached to improve performance.
	 * 
	 * @param imageView
	 *            - ImageView object
	 * @param height
	 *            - height dimension of the imageView
	 */
	public static void initializeImageView(ImageView imageView, double height) {
		imageView.setFitHeight(height);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setCache(true);
	}

}
