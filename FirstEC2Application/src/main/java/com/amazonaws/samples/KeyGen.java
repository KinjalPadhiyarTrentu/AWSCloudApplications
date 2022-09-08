package com.amazonaws.samples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;

public class KeyGen {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	private static final String FILENAME = "C:\\KINJAL\\newkp1.pem";

	public String generate() throws IOException {
		FileWriter fw = null;

		String keyname = "newkp1";
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonEC2Client amazonEC2Client = new AmazonEC2Client(awsCredentials);
		amazonEC2Client.setRegion(Region.getRegion(region));

		CreateKeyPairRequest cp = new CreateKeyPairRequest();
		cp.setKeyName("newkp1");

		CreateKeyPairResult cr = amazonEC2Client.createKeyPair(cp);
		String s = cr.getKeyPair().getKeyMaterial();

		fw = new FileWriter(FILENAME);
		fw.write(s);
		
		if(fw!=null)
		{
			fw.close();
		}
		System.out.println("Done");
		System.out.println(s);
		return keyname;
	}
}
