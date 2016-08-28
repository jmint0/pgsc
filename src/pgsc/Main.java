package pgsc;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import java.io.File;

public class Main {
	
	protected static void helpAndExit(int iReturnValue) {
		System.err.println("USAGE: pgsc [-h] [-l]");
		System.err.println("  -h - This help message");
		System.err.println("  -l - Use labels for the first line");
		System.exit(iReturnValue);
	}
	
	protected static void printLabels() {
		System.out.print("CP,");
		System.out.print("Name,");
		System.out.print("HP,");
		System.out.print("Type,");
		System.out.print("Weight,");
		System.out.print("Height,");
		System.out.print("Stardust,");
		System.out.print("Candy,");
		System.out.print("Candy Type,");
		System.out.print("Powerup Stardust,");
		System.out.print("Powerup Candy,");
		System.out.println("Evolve Candy");
	}
	
	protected static List<String> parseOptions(String[] args) {
		
		GetOpt	go = new GetOpt("hl");
		Map<String,String> optionsFound = go.parseArguments(args);
		
		for (String key : optionsFound.keySet()) {
			char c = key.charAt(0);
			switch (c) {
			case 'h':
				helpAndExit(0);
				break;
			case 'l':
				printLabels();
				break;
			default:
				System.err.println("Unknown option in " +
						args[go.getOptInd()-1]);
				helpAndExit(0);
			} // switch options
		} // for all options
		
		return go.getFilenameList();
	} // parseOptions

	protected static void displayStats(String strFilename) {
		BytePointer outText;
		
		String	strCP = "";
		String	strName = "";
		String	strHP = "";
		String	strType = "";
		String	strWeight = "";
		String	strHeight = "";
		String	strStardust = "";
		String	strCandy = "";
		String	strCandyPokemon = "";
		String	strPowerupStardust = "";
		String	strPowerupCandy = "";
		String	strEvolveCandy = "";
	//	String	strAttack1 = "";
	//	String	strAttack1Damage = "";
		
//		System.out.println(" ## System.getenv(\"TESSDATA_PREFIX\") = " + System.getenv("TESSDATA_PREFIX"));
		
		TessBaseAPI api = new TessBaseAPI();
		// Initialize tesseract-ocr with English, without specifying tessdata path
		if (api.Init(System.getenv("TESSDATA_PREFIX"), "ENG") != 0) {
			System.err.print("Could not initialize tesseract.");
			System.exit(1);
		}
		
		// Open input image with leptonica library
		PIX image = pixRead(strFilename);
		if ( image == null ) {
			System.out.println(" !! pixRead unable to open file" + strFilename);
		} else {
//			System.out.println(" ## Image width: " + image.w());
//			System.out.println(" ## Image height: " + image.h());
			api.SetImage(image);
			
			// CP
			api.SetVariable("tessedit_char_whitelist", "CP1234567890");
			api.SetRectangle(119, 117, 792, 102);
			outText = api.GetUTF8Text();
			strCP = outText.getString();
			outText.putString("");
	
			// Name
			api.SetVariable("tessedit_char_whitelist", "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			api.SetRectangle(100, 880, 106, 110);
			outText = api.GetUTF8Text();
			strName = outText.getString();
			outText.putString("");
	
			// HP
			api.SetVariable("tessedit_char_whitelist", "HP/1234567890 ");
			api.SetRectangle(301, 1013, 488, 58);
			outText = api.GetUTF8Text();
			strHP = outText.getString();
			outText.putString("");
					
			// Type
			api.SetVariable("tessedit_char_whitelist", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/");
			api.SetRectangle(55, 1123, 446, 76);
			outText = api.GetUTF8Text();
			strType = outText.getString();
			outText.putString("");
			
			// Weight
			api.SetVariable("tessedit_char_whitelist", ".1234567890kgm");
			api.SetRectangle(519, 1125, 246, 74);
			outText = api.GetUTF8Text();
			strWeight = outText.getString();
			outText.putString("");
			
			// Height
			api.SetVariable("tessedit_char_whitelist", ".1234567890mck");
			api.SetRectangle(785, 1125, 254, 74);
			outText = api.GetUTF8Text();
			strHeight = outText.getString();
			outText.putString("");
			
			// Stardust
			api.SetVariable("tessedit_char_whitelist", "1234567890");
			api.SetRectangle(127, 1331, 382, 60);
			outText = api.GetUTF8Text();
			strStardust = outText.getString();
			outText.putString("");
			
			// Candy
			api.SetVariable("tessedit_char_whitelist", "1234567890");
			api.SetRectangle(510, 1331, 487, 60);
			outText = api.GetUTF8Text();
			strCandy = outText.getString();
			outText.putString("");
			
			// Candy Pokemon
			api.SetVariable("tessedit_char_whitelist", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			api.SetRectangle(521, 1400, 500, 43);
			outText = api.GetUTF8Text();
			strCandyPokemon = outText.getString();
			outText.putString("");
			
			// Powerup Stardust
			api.SetVariable("tessedit_char_whitelist", "1234567890");
			api.SetRectangle(597, 1535, 150, 50);
			outText = api.GetUTF8Text();
			strPowerupStardust = outText.getString();
			outText.putString("");
			
			// Powerup Candy
			api.SetVariable("tessedit_char_whitelist", "1234567890");
			api.SetRectangle(817, 1535, 150, 50);
			outText = api.GetUTF8Text();
			strPowerupCandy = outText.getString();
			outText.putString("");
			
			// Evolve Candy
			api.SetVariable("tessedit_char_whitelist", "1234567890");
			api.SetRectangle(817, 1687, 150, 54);
			outText = api.GetUTF8Text();
			strEvolveCandy = outText.getString();
			outText.putString("");
	/*
	*  Removed because not in basic image. This is off the screen to the bottom. In order to get it you have to scroll up.			
	
			api.SetVariable("tessedit_char_whitelist", "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			api.SetRectangle(63, 1823, 430, 66);
			outText = api.GetUTF8Text();
			strAttack1 = outText.getString();
			outText.putString("");			
			
			api.SetVariable("tessedit_char_whitelist", "1234567890");
			api.SetRectangle(657, 1823, 362, 60);
			outText = api.GetUTF8Text();
			strAttack1Damage = outText.getString();
			outText.putString("");
	*/
	
			System.out.print(strCP.trim().trim() + ",");
			System.out.print(strName.trim() + ",");
			System.out.print(strHP.trim() + ",");
			System.out.print(strType.trim() + ",");
			System.out.print(strWeight.trim() + ",");
			System.out.print(strHeight.trim() + ",");
			System.out.print(strStardust.trim() + ",");
			System.out.print(strCandy.trim() + ",");
			System.out.print(strCandyPokemon.trim() + ",");
			System.out.print(strPowerupStardust.trim() + ",");
			System.out.print(strPowerupCandy.trim() + ",");
			System.out.println(strEvolveCandy.trim());
	//		System.out.print(strAttack1.trim() + ",");
	//		System.out.println(strAttack1Damage.trim());
			
			outText.deallocate();
			pixDestroy(image);

		} // good image
		
		// Destroy used object and release memory
		api.End();
		api.close();
				
	} // displayStats

	public static void main(String[] args) throws Exception {
		
		File	fileF;
		List<String> listFiles;
		listFiles = parseOptions(args);
		
//		System.out.println(" ## parsedOptions");
		
		if ( listFiles.size() < 1 ) {
			listFiles.add(".");	// Set it for current directory if there are no file arguments
//			System.out.println(" ## added current directory for default");
		}
		
		for (String strFile : listFiles) {	// Check down through the list of files sent by the user
			
//			System.out.println(" ## trying to get a new file");
			fileF = new File(strFile);
//			System.out.println(" ## trying to open " + strFile);
			if ( ( fileF == null ) || !fileF.exists() ){
				System.err.println(" !! Could not open file " + strFile);
			}
			else 
			{
				if ( fileF.isDirectory() ) {
				
					File fileYaF;
					
//					System.out.println(" ## trying to list the contents of a directory");
					
					String[] dirs = new java.io.File(strFile).list(); // Get list of names
					Arrays.sort(dirs);
					for (String dir : dirs) {	
//						System.out.println(" ## trying to review " + strFile + "\\" + dir);
						fileYaF = new File(strFile + "\\" + dir);
						if ( ( fileYaF == null ) || !fileYaF.exists() ) {
							System.err.println(" !! Could not open file " + strFile + "\\" + dir);
						}
						else
						{
							if ( fileYaF.isFile() ) 
								displayStats(strFile + "\\" + dir);
						}
					} // for each listing in a directory
					
				}
				else
				{
					displayStats(strFile);
				}
			} // if not error on command line file argument
				
		} // for each command line file argument
		
	} // main

}
