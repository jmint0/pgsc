package pgsc;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;


public class Main {

	public static void main(String[] args) throws Exception {
		BytePointer outText;
		
		System.out.println(" ## System.getenv(\"TESSDATA_PREFIX\") = " + System.getenv("TESSDATA_PREFIX"));
		
		TessBaseAPI api = new TessBaseAPI();
		// Initialize tesseract-ocr with English, without specifying tessdata path
		if (api.Init(System.getenv("TESSDATA_PREFIX"), "ENG") != 0) {
			System.err.print("Could not initialize tesseract.");
			System.exit(1);
		}
		
		// Open input image with leptonica library
		PIX image = pixRead("C:\\Users\\jminto\\Pictures\\Pokemon\\Screenshot_2016-07-27-08-18-59.png");
		if ( image == null ) {
			System.out.println(" !! pixRead unable to open file");
		} else {
			System.out.println(" ## Image width: " + image.w());
			System.out.println(" ## Image height: " + image.h());
			api.SetImage(image);
			// Get OCR result
			outText = api.GetUTF8Text();
			String string = outText.getString();
			if ( string.isEmpty() ) 
				System.out.println(" !! OCR did not detect string.");
			else 
				System.out.println("OCR output:\n" + string);
			outText.deallocate();
		} // good image
		
		// Destroy used object and release memory
		api.End();

		pixDestroy(image);
		
	} // main

}
