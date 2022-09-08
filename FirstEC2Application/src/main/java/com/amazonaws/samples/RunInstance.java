package com.amazonaws.samples;


import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

public class RunInstance
{
	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	public static void main(String[] args)
	{
	try
	{
		KeyGen k = new KeyGen();
		AWSCredentials awsCredentials=new BasicAWSCredentials(accessKey,secretKey);
		
		AmazonEC2Client amazonEC2Client=new AmazonEC2Client(awsCredentials);
		amazonEC2Client.setRegion(Region.getRegion(region));
		
		RunInstancesRequest instancesRequest=new RunInstancesRequest("ami-4191b524",1,1);
		instancesRequest.setInstanceType(InstanceType.T2Micro);
		instancesRequest.setKeyName(k.generate());
		
		RunInstancesResult instancesResult=amazonEC2Client.runInstances(instancesRequest);
		
		Reservation reservation=instancesResult.getReservation();
		List<com.amazonaws.services.ec2.model.Instance> instances = reservation.getInstances();
		
		for(com.amazonaws.services.ec2.model.Instance instance:instances)
		{
			System.out.println("Instance Id"+instance.getInstanceId());
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}
}