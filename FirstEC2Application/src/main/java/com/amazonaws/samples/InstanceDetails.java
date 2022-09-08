package com.amazonaws.samples;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class InstanceDetails {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	public static void main(String args[])
	{
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonEC2Client amazonEC2Client = new AmazonEC2Client(awsCredentials);
		amazonEC2Client.setRegion(Region.getRegion(region));

		List<String> ls = new ArrayList();
		ls.add("i-0110e52a7c3933282");
		ls.add("i-0f1814743580351fa");
		
		DescribeInstancesRequest dr = new DescribeInstancesRequest();
		dr.setInstanceIds(ls);
		
		DescribeInstancesResult ds = amazonEC2Client.describeInstances(dr);
		
		List<Reservation> res = ds.getReservations();
		
		for(Reservation reser: res)
		{
			for(Instance i:reser.getInstances())
			{
				System.out.println(i.getInstanceId()+" + "+i.getPlatform()+" + "+i.getState().getName());
				
			}
		}
	}
	
}
