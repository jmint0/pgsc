package pgsc;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "Ikea_Lady_Walking.jpg";
		System.out.println(" ## Loading Image " + filename);
        IplImage image = cvLoadImage(filename);
        if (image != null) {
        	System.out.println(" ## Smothing Image...");
            cvSmooth(image, image);
            System.out.println(" ## Saving Image...");
            cvSaveImage(filename, image);
            cvReleaseImage(image);
        }
        System.out.println(" ## Code Complete.");
	}

}
