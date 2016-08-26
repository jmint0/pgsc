package pgsc;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;


public class Main {

	public static void main(String[] args) throws Exception {
		BytePointer outText;
		
		String	strCP = "";
		String	strName = "";
		String	strType = "";
		String	strWeight = "";
		String	strHeight = "";
		String	strStardust = "";
		String	strCandy = "";
		String	strCandyPokemon = "";
		String	strPowerupStardust = "";
		String	strPowerupCandy = "";
		String	strEvolveCandy = "";
		String	strAttack1 = "";
		String	strAttack1Damage = "";
		
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
			
			api.SetRectangle(230, 35, 620, 86);
			outText = api.GetUTF8Text();
			strCP = outText.getString();
			outText.putString("");

			api.SetRectangle(37, 775, 996, 112);
			outText = api.GetUTF8Text();
			strName = outText.getString();
			outText.putString("");
		
			api.SetRectangle(35, 1029, 356, 72);
			outText = api.GetUTF8Text();
			strType = outText.getString();
			outText.putString("");
		
			api.SetRectangle(403, 1029, 292, 72);
			outText = api.GetUTF8Text();
			strWeight = outText.getString();
			outText.putString("");
			
			api.SetRectangle(713, 1029, 332, 72);
			outText = api.GetUTF8Text();
			strHeight = outText.getString();
			outText.putString("");
			
			api.SetRectangle(267, 1217, 352, 80);
			outText = api.GetUTF8Text();
			strStardust = outText.getString();
			outText.putString("");
			
			api.SetRectangle(717, 1217, 322, 80);
			outText = api.GetUTF8Text();
			strCandy = outText.getString();
			outText.putString("");
			
			api.SetRectangle(513, 1313, 524, 69);
			outText = api.GetUTF8Text();
			strCandyPokemon = outText.getString();
			outText.putString("");
			
			api.SetRectangle(595, 1441, 156, 50);
			outText = api.GetUTF8Text();
			strPowerupStardust = outText.getString();
			outText.putString("");
			
			api.SetRectangle(813, 1441, 158, 50);
			outText = api.GetUTF8Text();
			strPowerupCandy = outText.getString();
			outText.putString("");
			
			api.SetRectangle(813, 1599, 158, 50);
			outText = api.GetUTF8Text();
			strEvolveCandy = outText.getString();
			outText.putString("");
			
			api.SetRectangle(63, 1823, 430, 66);
			outText = api.GetUTF8Text();
			strAttack1 = outText.getString();
			outText.putString("");			
			
			api.SetRectangle(657, 1823, 362, 60);
			outText = api.GetUTF8Text();
			strAttack1Damage = outText.getString();
			outText.putString("");


			System.out.print(strCP + ", ");
			System.out.print(strName + ", ");
			System.out.print(strType + ", ");
			System.out.print(strWeight + ", ");
			System.out.print(strHeight + ", ");
			System.out.print(strStardust + ", ");
			System.out.print(strCandy + ", ");
			System.out.print(strCandyPokemon + ", ");
			System.out.print(strPowerupStardust + ", ");
			System.out.print(strPowerupCandy + ", ");
			System.out.print(strEvolveCandy + ", ");
			System.out.print(strAttack1 + ", ");
			System.out.println(strAttack1Damage);
			
			outText.deallocate();
		} // good image
		
		// Destroy used object and release memory
		api.End();

		pixDestroy(image);
		
	} // main

}
