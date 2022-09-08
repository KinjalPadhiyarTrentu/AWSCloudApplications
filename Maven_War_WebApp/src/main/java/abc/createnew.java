package abc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

public class createnew {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	
	AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
	AmazonEC2Client amazonEC2Client = new AmazonEC2Client(awsCredentials);

	public void createinstance(String name, String amiid) throws IOException {
		FileWriter fw = null;
		amazonEC2Client.setRegion(Region.getRegion(region));

		final String fname = "C:\\KINJAL\\" + name;

		CreateKeyPairRequest cp = new CreateKeyPairRequest();
		cp.setKeyName(name);
		CreateKeyPairResult cr = amazonEC2Client.createKeyPair(cp);
		String s = cr.getKeyPair().getKeyMaterial();

		fw = new FileWriter(fname);
		fw.write(s);

		if (fw != null) {
			fw.close();
		}

		RunInstancesRequest instancesRequest = new RunInstancesRequest(amiid, 1, 1);
		instancesRequest.setInstanceType(InstanceType.T2Micro);
		instancesRequest.setKeyName(name);

		RunInstancesResult instancesResult = amazonEC2Client.runInstances(instancesRequest);

		Reservation reservation = instancesResult.getReservation();
		List<com.amazonaws.services.ec2.model.Instance> instances = reservation.getInstances();

		for (com.amazonaws.services.ec2.model.Instance instance : instances) {
			System.out.println("Instance Id" + instance.getInstanceId());
		}

	}

	public void createinstanceexist(String name, String amiid) throws IOException {
		amazonEC2Client.setRegion(Region.getRegion(region));

		RunInstancesRequest instancesRequest = new RunInstancesRequest(amiid, 1, 1);
		instancesRequest.setInstanceType(InstanceType.T2Micro);
		instancesRequest.setKeyName(name);

		RunInstancesResult instancesResult = amazonEC2Client.runInstances(instancesRequest);

		Reservation reservation = instancesResult.getReservation();
		List<com.amazonaws.services.ec2.model.Instance> instances = reservation.getInstances();

		for (com.amazonaws.services.ec2.model.Instance instance : instances) {
			System.out.println("Instance Id" + instance.getInstanceId());
		}

	}
}
