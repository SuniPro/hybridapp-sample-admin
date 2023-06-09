package com.hybirdapp.sample.cmmn.fileUtil.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.cmmn.script.service.AttachVO;
import com.hybirdapp.sample.cmmn.script.service.impl.ScriptDAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("CmmnFileSaveService")
public class CmmnFileSaveServiceImpl implements CmmnFileSaveService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Value("${file.upload.path}")
	private String rootPath;
	
	@Value("${Image.upload.path}")
	private String imagePath;
	
	@Value("${Image.upload.url}")
	private String imagePathUrl;
	
	@Resource(name = "ScriptDAO")
	private ScriptDAO scriptDAO;
	
	/***
	 * 뽑고자 하는 파일의 Name을 받아서 처리함
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, Object>>  saveFindNameMultipartFiles(HttpServletRequest req, final String dir, final String uploadNm) throws Exception {
		
		MultipartHttpServletRequest multi = (MultipartHttpServletRequest) req;
		
		String sOptionalPath = StringUtils.defaultIfBlank(dir, "");
		
		List<Map<String, Object>> retList = null;
		
		String fileNames = CmmnConstant.UPLOAD_FILE_NM;
		
		if (uploadNm != null && !"".equals(uploadNm)) {
			fileNames = uploadNm;
		}
		
		List<MultipartFile> mfileList  = multi.getFiles(fileNames);
		
		if (mfileList != null && mfileList.size() > 0 && !"".equals(mfileList.get(0).getOriginalFilename())) {
			
			retList = new ArrayList<Map<String, Object>>();
			
			for (MultipartFile mfile : mfileList) {
				retList.add(saveFile(mfile, sOptionalPath));
			}
		}
		
		return retList;
	}
	
	/***
	 * 파일 테이블에 등록후 key를 돌려줌
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Map<String, List<String>> saveFileResultKey(HttpServletRequest req, final String dir, final String uploadNm) throws Exception {
		
		MultipartHttpServletRequest multi = (MultipartHttpServletRequest) req;
		
		String sOptionalPath = StringUtils.defaultIfBlank(dir, "");
		
		Map<String, List<String>> resultKeyMap = null; 
		
		String fileNames = CmmnConstant.UPLOAD_FILE_NM;
		
		if (uploadNm != null && !"".equals(uploadNm)) {
			fileNames = uploadNm;
		}
		
		List<MultipartFile> mfileList  = multi.getFiles(fileNames);
		
		String userSn = SessionUtil.getAttributeStr(CmmnConstant.SESSION_USER, CmmnConstant.USER_SN);
		
		if (mfileList != null && mfileList.size() > 0 && !"".equals(mfileList.get(0).getOriginalFilename())) {
			
			resultKeyMap = new HashMap<String, List<String>>();
			List<String> keyList = new ArrayList<String>();
			Map<String, Object> resultGrpMap = new HashMap<String, Object>();
			resultGrpMap.put("userSn", userSn);
			resultGrpMap.put("fileGrpSe",null);
			int fileSn = scriptDAO.insertGrpFile(resultGrpMap);
			for (MultipartFile mfile : mfileList) {
				Map<String, Object> resultMap = saveFile(mfile, sOptionalPath);
				if(!resultMap.isEmpty()) {
					resultMap.put("userSn", userSn);
					resultMap.put("fileGrpSn", resultGrpMap.get("fileGrpSn"));
					resultMap.put("fileSn", fileSn++);
					scriptDAO.insertFile(resultMap);
					keyList.add(String.valueOf(fileSn));
				}
			}
			resultKeyMap.put(CmmnConstant.UPLOAD_FILE_KEY, keyList);
		}
		
		return resultKeyMap;
	}
	
	/***
	 * 파일 테이블에 등록후 key를 돌려줌
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String saveFileList(List<MultipartFile> mfileList, final String dir, final String fileGrpSe, final String pFileGrpSn) throws Exception {
		
		String fileSn = "";
		
		String sOptionalPath = StringUtils.defaultIfBlank(dir, "");
		
		String userSn= (SessionUtil.getAttributeStr(CmmnConstant.SESSION_USER, CmmnConstant.USER_SN)== "")  ? SessionUtil.getAttributeStr(CmmnConstant.SESSION_MNGR, CmmnConstant.MNGR_ID) : SessionUtil.getAttributeStr(CmmnConstant.SESSION_USER, CmmnConstant.USER_SN);
		
		if (mfileList != null && mfileList.size() > 0 && !"".equals(mfileList.get(0).getOriginalFilename())) {

			if (!"".equals(pFileGrpSn) && pFileGrpSn != null) {

				fileSn = pFileGrpSn;
			
			} else {
				
				Map<String, Object> resultGrpMap = new HashMap<String, Object>();
				
				resultGrpMap.put("userSn"	, userSn);
				resultGrpMap.put("fileGrpSe", fileGrpSe);

				scriptDAO.insertGrpFile(resultGrpMap);
				fileSn = String.valueOf(resultGrpMap.get("fileGrpSn"));
			}

			for (MultipartFile mfile : mfileList) {
				
				Map<String, Object> resultMap = saveFile(mfile, sOptionalPath);
				
				if(!resultMap.isEmpty()) {
					
					resultMap.put("userSn", userSn);
					resultMap.put("fileGrpSn"	, fileSn);
					
					scriptDAO.insertFile(resultMap);
				}
			}
		}

		return fileSn;
	}
	
	
	/***
	 * 컨텐츠 추출
	 * @param path
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	@Override
	public String readFileContent(String path) throws Exception {
		
		InputStreamReader fis = new InputStreamReader(new FileInputStream(path), "UTF-8");
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(fis);
		
		StringBuffer sb = new StringBuffer();
		
		while (scanner.hasNext()) {
			sb.append(scanner.nextLine());
		}
		
		return sb.toString();
	}

	/***
	 * 파일 삭제
	 * @param fileUrl
	 * @throws IOException
	 */
	@Override
	public void deleteFileUrl(String fileUrl) throws Exception {
		File f = new File(fileUrl);
		f.delete();
	}
	
	/***
	 * 파일 삭제
	 * @param fileUrl
	 * @throws IOException
	 */
	@Override
	public void deleteFile(String boardSe, int boardSn) throws Exception {
		
		if (boardSe !=null && !"".equals(boardSe)) {

			AttachVO attachVO = new AttachVO();
			
			attachVO.setBoardSe(boardSe);
			attachVO.setBoardSn(boardSn);
			
			AttachVO resultFile = scriptDAO.selectAttachInfo(attachVO);
			int resCnt = scriptDAO.deleteFile(attachVO);
			String url = resultFile.getSavePath()+ resultFile.getNewFileNm();
			if (resCnt > 0) {
				
				String filePath = String.valueOf(url);
				
				if (!"".equals(filePath) && filePath != null) {
					File f = new File(filePath);
					f.delete();
				}
			}
		}
	}
	
	/***
	 * 파일 삭제
	 * @param fileUrl
	 * @throws IOException
	 */
	@Override
	public AttachVO selectFile(String boardSe, int boardSn) throws Exception {
		AttachVO attachVO = new AttachVO();
		
		attachVO.setBoardSe(boardSe);
		attachVO.setBoardSn(boardSn);
		
		return scriptDAO.selectAttachInfo(attachVO);
	}
	
	
	/***
	 * 파일 삭제
	 * @param fileUrl
	 * @throws IOException
	 */
	@Override
	public void deleteFileAll(String fileGrpSn) throws Exception {
//		
//		if (fileGrpSn !=null && !"".equals(fileGrpSn)) {
//
//			List<AttachVO> resultFileList = scriptDAO.selectAttachInfoList(fileGrpSn);
//			
//			if (resultFileList != null && resultFileList.size() > 0) {
//				
//				for (AttachVO attach : resultFileList) {
//					
//					int resCnt = scriptDAO.deleteFile(attach.getFileGrpSn(), attach.getFileSn());
//					
//					if (resCnt > 0) {
//						
//						String filePath = String.valueOf(attach.getFilePath());
//						
//						if (!"".equals(filePath) && filePath != null) {
//							File f = new File(filePath);
//							f.delete();
//						}
//					}
//				}
//			}
//		}
	}
	
	/**
	 * 실제 파일 업로드
	 * @param file
	 * @param sOptionalPath
	 * @return
	 * @throws IOException
	 */
	@Override
	public Map<String, Object> saveFile(MultipartFile file, String sOptionalPath) throws Exception {
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		long fileSize = file.getSize();
		
		try {
				if(fileSize > 0) {
					
					UUID randomeUUID = UUID.randomUUID();
					
					// 임시 변수 프로퍼티 작업 되면 이후 프로퍼티 값으로 변경
					String uploadDir = CmmnConstant.UPLOAD_DEFAULT_DIR;
					
					if (StringUtils.isNotBlank(sOptionalPath)) {
						uploadDir = sOptionalPath + "/";
					}
					
					// 파일명
					String fileOrgNm = new String(file.getOriginalFilename().getBytes()); 
					// 파일 확장자
					String fileOrgExt = fileOrgNm.substring(file.getOriginalFilename().lastIndexOf(CmmnConstant.UPLOAD_EXT_INDEX));
					// 파일 경로
					String fileDirectory = imagePath + uploadDir;
					// 생성된 파일명
					String fileNewNm = randomeUUID + fileOrgExt;
					
					File directory = new File(fileDirectory);
					
					if(!directory.exists()){
						directory.mkdirs();
					}
					
					if (uploadDir.startsWith(File.separator)) {
						uploadDir = uploadDir.replaceFirst(File.separator, "");
					}
					
					// 파일 URL
					String fileFullPath = fileDirectory + fileNewNm;
					
					file.transferTo(new File(fileFullPath));
					
					fileMap.put(CmmnConstant.ORGN_FILE_NM		, fileOrgNm);					//원본파일명
					fileMap.put(CmmnConstant.NEW_FILE_NM		, fileNewNm);					//new 파일명
					fileMap.put(CmmnConstant.URL				, fileFullPath);				//파일 전체 경로 URL
					fileMap.put(CmmnConstant.SAVE_PATH			, fileDirectory);				//파일 경로 
//					fileMap.put(CmmnConstant.FILE_ORG_EXT		, fileOrgExt.substring(1));		//원본파일확장자
//					fileMap.put(CmmnConstant.FILE_SIZE	 		, fileSize);					//파일 사이즈
					
				}
				
		} catch (IOException ex) {
			logger.error("IGCmmnFileSaveServiceImpl.saveFile()", ex);
		}
		
		return fileMap;
		
	}

	/***
	 * 이미지 파일 저장 - 1건씩
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@Override
	public Map<String, Object> saveImageFile(final MultipartFile file, final String prefix, final Integer index) throws Exception {
		if (file == null || file.getOriginalFilename() == null || "".equals(file.getOriginalFilename())) {
			return null;
		}
		EgovMap egovMap = null;
		String fileNewNm = "";
		String url = "";
		String savePath = "";
		String fileFullPath = "";
		String uploadDir = "";
		String extension = null;
		try {
			extension = getExtension(file.getOriginalFilename());
			if (extension == null) {
				return null;
			}
			if ("png|jpg|gif".indexOf(extension) < 0) {
				return null;
			}
			egovMap = new EgovMap();
			fileNewNm = getFileName(prefix, index) + "." + extension;
			if (prefix != null) {
				uploadDir = prefix;
			}
			uploadDir = uploadDir + "/";
			url =  imagePathUrl + "/" + CmmnConstant.IMAGE_UPLOAD_DIR + uploadDir + fileNewNm;
			savePath = imagePath + "/" + CmmnConstant.IMAGE_UPLOAD_DIR + uploadDir;

			File f = new File(savePath);
			if(!f.exists()){
				f.mkdirs();
			}

			fileFullPath = f + "/" + fileNewNm;
			file.transferTo(new File(fileFullPath));

			egovMap.put(CmmnConstant.ORGN_FILE_NM, file.getOriginalFilename());				//원본파일명
			egovMap.put(CmmnConstant.NEW_FILE_NM, fileNewNm);				//new 파일명
			egovMap.put(CmmnConstant.URL, url);						//파일 전체 경로 URL
			egovMap.put(CmmnConstant.SAVE_PATH, savePath);				//파일 경로
			egovMap.put(CmmnConstant.EXPSR_ORDR, index);				//파일 순서
		} catch (IOException ex) {
			logger.error("IGCmmnFileSaveServiceImpl.saveFile()", ex);
		}


		return egovMap;
	}

	@Override
	public void deleteImageFile(final String prefix, final String fileName) throws Exception {
		String uploadDir = "";
		if (prefix != null) {
			uploadDir = prefix;
		}
		String savePath = imagePath + "/" + CmmnConstant.IMAGE_UPLOAD_DIR + uploadDir;
		File f = new File(savePath + fileName);
		if(!f.exists()){
			f.delete();
		}
	}

	private String getFileName(final String prefix, final Integer index) {
		String fileName = String.valueOf(System.currentTimeMillis());
		if (prefix != null) {
			fileName = prefix + "_" + fileName;
		}
		if (index != null) {
			fileName = fileName + "_" + String.valueOf(index);
		}

		return fileName;
	}

	private String getExtension(final String fileName) {
		if (fileName == null) {
			return null;
		}
		String ext = null;
		if (fileName.lastIndexOf(".") >= 0) {
			ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			ext = ext.toLowerCase();
		}

		return ext;
	}

	/**
	 * 실제 멀티 파일 업로드
	 * @param file
	 * @param prxName
	 * @return
	 * @throws IOException
	 */
	@Override
	public List<Map<String, Object>> saveFileMulti(MultipartFile[] file, String prxName) throws Exception {
		
		List<Map<String, Object>> fileMap = new ArrayList<Map<String, Object>>();
		long fileSize = file.length;
		
		String fileOrgNm = ""; 
		String fileNewNm = "";
		String url = "";
		String savePath = "";
		String fileFullPath = "";
		String uploadDir = "";
		int expsrOrdr = 1;
		try {
			if(fileSize > 0) {
				for(int i=0; i<file.length; i++) {
					if(!"".equals(file[i].getOriginalFilename())) {
						EgovMap egovMap = new EgovMap();
						fileOrgNm = file[i].getOriginalFilename();
						 
						 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss_"+expsrOrdr); 
						 Calendar now = Calendar.getInstance();  
						 SimpleDateFormat fileFolder = new SimpleDateFormat("yyyyMMdd"+""); 
						 
						 String extension = fileOrgNm.split("\\.")[1]; //fileOriginName에 날짜+.+확장자명으로 저장시킴. 
						 
						 fileNewNm = prxName+"_"+formatter.format(now.getTime())+"."+extension; 
						 uploadDir = fileFolder.format(now.getTime()) +"/";
						 url =  imagePathUrl+"/"+CmmnConstant.IMAGE_UPLOAD_DIR + uploadDir+fileNewNm; 
						 
						 savePath = imagePath +"/"+CmmnConstant.IMAGE_UPLOAD_DIR + uploadDir;
						 
						 File f = new File(savePath);
						 
						 if(!f.exists()){
							f.mkdirs();
						 }
							
						 fileFullPath = f +"/"+fileNewNm;
						 file[i].transferTo(new File(fileFullPath)); 
						 
						 egovMap.put(CmmnConstant.ORGN_FILE_NM		, fileOrgNm);				//원본파일명
						 egovMap.put(CmmnConstant.NEW_FILE_NM		, fileNewNm);				//new 파일명
						 egovMap.put(CmmnConstant.URL				, url);						//파일 전체 경로 URL
						 egovMap.put(CmmnConstant.SAVE_PATH			, savePath);				//파일 경로 
						 egovMap.put(CmmnConstant.EXPSR_ORDR		, expsrOrdr);				//파일 순서 
						 expsrOrdr++;
						 fileMap.add(egovMap);
					}
				}
			}
		} catch (IOException ex) {
			logger.error("IGCmmnFileSaveServiceImpl.saveFile()", ex);
		}
		
		return fileMap;
		
	}

	/**
	 * 에이터 실제 파일 업로드
	 * @param file
	 * @param sOptionalPath
	 * @return
	 * @throws IOException
	 */
	@Override
	public String editorsaveFile(MultipartFile file, String sOptionalPath) throws Exception {
		
		String filePath = "";
		long fileSize = file.getSize();
		String fileNewNm = "";
		String url = "";
		String savePath = "";
		String fileFullPath = "";
		try {
			if(fileSize > 0) {
				 String fileOrgNm = new String(file.getOriginalFilename().getBytes()); 
				 
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss"); 
				 Calendar now = Calendar.getInstance();  
				 
				 String extension = fileOrgNm.split("\\.")[1]; //fileOriginName에 날짜+.+확장자명으로 저장시킴. 
				 
				 fileNewNm = formatter.format(now.getTime())+"."+extension; 
//					 url =  sOptionalPath +imagePath + CmmnConstant.IMAGE_UPLOAD_DIR +fileNewNm; 
				 url =  sOptionalPath+imagePathUrl+"/"+CmmnConstant.IMAGE_UPLOAD_DIR_EDITOR +fileNewNm; 
				 
				 savePath = imagePath +"/"+ CmmnConstant.IMAGE_UPLOAD_DIR_EDITOR;
				 
				 File f = new File(savePath);
				 
				 if(!f.exists()){
					f.mkdirs();
				 }
				 
				 fileFullPath = f +"/"+fileNewNm;
				 file.transferTo(new File(fileFullPath));
				 filePath = url;
			}
				
		} catch (IOException ex) {
			logger.error("EditorsaveFile.saveFile()", ex);
		}
		
		return filePath;
		
	}
	
	/***
	 * 파일 목록 조회
	 */
	@Override
	public List<AttachVO> searchFileList(String fileGrpSn) throws Exception {
		return scriptDAO.selectAttachInfoList(fileGrpSn);
	}
	

    //    각종파일 추출기능
    //    http://develop.sunshiny.co.kr/913 및 https://github.com/ddoleye/java-hwp 참고
    /*
    private String getStringTextFromTextFile(String fileURL) throws Exception 
    {
        StringBuffer sb = new StringBuffer();
        
        BufferedReader in = new BufferedReader(new FileReader(fileURL));
        String tempOneLine;
        while ((tempOneLine = in.readLine()) != null) 
        {
            sb.append(tempOneLine);
        }
        in.close();
        
        return sb.toString();
    }
 
    private String getStringTextFromHWP(String fileURL) throws Exception 
    {
        checkValidationForFileInfo(fileURL);
        
        File hwp = new File(fileURL); // 텍스트를 추출할 HWP 파일
        Writer writer = new StringWriter(); // 추출된 텍스트를 출력할 버퍼
        HwpTextExtractor.extract(hwp, writer); // 파일로부터 텍스트 추출
        String text = writer.toString(); // 추출된 텍스트
        
        return text;
    }
 
    private String getStringTextFromXLSX(String fileURL) throws Exception 
    {
        checkValidationForFileInfo(fileURL);
        
        FileInputStream fs = new FileInputStream(new File(fileURL));
        OPCPackage d = OPCPackage.open(fs);
        XSSFExcelExtractor xe = new XSSFExcelExtractor(d);
        xe.setFormulasNotResults(true);
        xe.setIncludeSheetNames(true);
        return xe.getText();
    }
 
    private String getStringTextFromPPTX(String fileURL) throws Exception
    {
        checkValidationForFileInfo(fileURL);
        
        FileInputStream fs = new FileInputStream(new File(fileURL));
        OPCPackage d = OPCPackage.open(fs);
        XSLFPowerPointExtractor xp = new XSLFPowerPointExtractor(d);
        return xp.getText();
    }
 
    private String getStringTextFromDOCX(String fileURL) throws Exception 
    {
        checkValidationForFileInfo(fileURL);
        
        FileInputStream fs = new FileInputStream(new File(fileURL));
        OPCPackage d = OPCPackage.open(fs);
        XWPFWordExtractor xw = new XWPFWordExtractor(d);
        return xw.getText();
    }
 
    private String getStringTextFromPPT(String fileURL) throws Exception
    {
        checkValidationForFileInfo(fileURL);
        
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileURL));
        PowerPointExtractor extractor = new PowerPointExtractor(fs);
        return extractor.getText();
    }
 
    private String getStringTextFromXLS(String fileURL) throws Exception
    {
        checkValidationForFileInfo(fileURL);
        
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileURL));
        ExcelExtractor ex = new ExcelExtractor(fs);
        ex.setFormulasNotResults(true);
        ex.setIncludeSheetNames(true);
        return ex.getText();
    }
 
    //    DOC 파일추출기능    /    
    private String getStringTextFromDOC(String fileURL) throws Exception
    {
        checkValidationForFileInfo(fileURL);
        
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileURL));
        HWPFDocument doc = new HWPFDocument(fs);
        WordExtractor we = new WordExtractor(doc); 
        return we.getText();
    }
 
    //    PDF 파일 추출기능    /    오류 발생시 모두 Exception으로 처리하자.
    private String getStringTextFromPDF(String fileURL) throws Exception 
    {
        checkValidationForFileInfo(fileURL);
        
        String resultText = "";
        
        FileInputStream fi = new FileInputStream(new File(fileURL));
        PDFParser parser = new PDFParser(fi);
        parser.parse();
        COSDocument cd = parser.getDocument();
        PDFTextStripper stripper = new PDFTextStripper();
        resultText = stripper.getText(new PDDocument(cd));
        cd.close();
        
        return resultText;
    }
 
    private void checkValidationForFileInfo(String fileURL) {
        if(fileURL==null || StringUtils.isEmpty(fileURL) || StringUtils.isEmpty(fileURL))
        {
            throw new IllegalArgumentException("입력된 파일 정보가 올바르지 않습니다.");
        }
    }	
	
	*/
}
