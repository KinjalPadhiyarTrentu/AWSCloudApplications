package Task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.util.IOUtils;

public class check extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "Compdata";
	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_WEST_2;
	ByteBuffer sourcebb = null;
	ByteBuffer destbb = null;
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	private static final int pqr = 1;
	File storeFile;
	private static String image_path = "";
	static Image i1, i2;

	public check() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filePath = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);

		String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;

		try {
			List<FileItem> formItems = upload.parseRequest(request);
			int i = 0;
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {

					if (!item.isFormField()) {
						String fileName = "Kinjalxyz" + i + ".jpg";
						image_path = pqr + "/" + fileName;

						filePath = uploadPath + "/" + image_path;
						storeFile = new File(filePath);

						item.write(storeFile);

						System.out.println("Image: " + storeFile.getPath());
						if (i == 0) {
							InputStream is = new FileInputStream(storeFile);
							sourcebb = ByteBuffer.wrap(IOUtils.toByteArray(is));
							i1 = new Image().withBytes(sourcebb);
						}
						if (i == 1) {
							InputStream is = new FileInputStream(storeFile);
							destbb = ByteBuffer.wrap(IOUtils.toByteArray(is));
							i2 = new Image().withBytes(destbb);
						}
						i++;
					}
				}
			}

			AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
			AmazonRekognitionClient r = new AmazonRekognitionClient(awsCredentials);
			r.setRegion(Region.getRegion(region));

			CompareFacesRequest req = new CompareFacesRequest().withSourceImage(i1).withTargetImage(i2);

			CompareFacesResult res = r.compareFaces(req);

			List<CompareFacesMatch> faceDetails = res.getFaceMatches();
			List<ComparedFace> unmatch = res.getUnmatchedFaces();

			for (CompareFacesMatch match : faceDetails) {

				System.out.println("Answer: " + match.getSimilarity() + " Hence almost similar");

			}
			for (ComparedFace match : unmatch) {

				System.out.println("Answer: " + match.getConfidence() + " Hence almost dissimilar");

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}