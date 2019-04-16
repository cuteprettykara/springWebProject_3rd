package org.zerock.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadUtils {
	private static final Logger log = LoggerFactory.getLogger(UploadUtils.class);
	
	public static String uploadFile(
			String uploadPath, 
			String originalFilename, 
			byte[] fileData) throws IOException {
		
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalFilename;
		String savedPath = calcPath(uploadPath);	// 폴더 생성
		
		File target = new File(uploadPath + savedPath, savedName);
		log.info("target: {}", target);
		
		FileCopyUtils.copy(fileData, target);
		
		// 이하 썸네일 생성하기
		String formatName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		
		String uploadedFileName = null;
		
		if (MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		
		return uploadedFileName;
	}

	private static String calcPath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		
		String monthPath = yearPath
				+ File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		
		String datePath = monthPath
				+ File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, datePath);
		
		return datePath;
	}

	private static void makeDir(String uploadPath, String datePath) {
		File dirPath = new File(uploadPath + datePath);

 		if (!dirPath.exists()) 	dirPath.mkdirs();
	}
	
	
	private static String makeIcon(String uploadPath, String path, String savedName) {
		String iconName = uploadPath + path + File.separator + savedName;
		
		// 브라우저에서 윈도우의 경로로 사용하는 '\' 문자가 정상적인 경로로 인식되지 않기 때문에 '/'로 치환해 줍니다.
		String replacedName = iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
		log.info("iconName    : {}", iconName);
		log.info("replacedName: {}", replacedName);
		
		return replacedName;
	}
	
	private static String makeThumbnail(
			String uploadPath,
			String path,
			String fileName) throws IOException {
	
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// 브라우저에서 윈도우의 경로로 사용하는 '\' 문자가 정상적인 경로로 인식되지 않기 때문에 '/'로 치환해 줍니다.
		String replacedName = thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		log.info("thumbnailName: {}", thumbnailName);
		log.info("replacedName : {}", replacedName);
		
		return replacedName;
	}
}
