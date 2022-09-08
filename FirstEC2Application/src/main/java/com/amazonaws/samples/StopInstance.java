package com.amazonaws.samples;


import java.util.*;

import javax.swing.plaf.basic.BasicArrowButton;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.services.workspaces.model.RunningMode;

public class StopInstance {
	
	/*
	private static String accessKey="#";
	private static String secretKey="#";
	*/
	private static Regions region=Regions.US_EAST_2;
	private static String pn = "default";
	
	public static void main(String[] args) {
		try{
			
			ProfileCredentialsProvider pcp = new ProfileCredentialsProvider(pn);

			//AWSCredentials awsCredentials=new BasicAWSCredentials(accessKey, secretKey);
			//AmazonEC2Client amazonEC2Client=new AmazonEC2Client(awsCredentials);
			
			AmazonEC2Client amazonEC2Client=new AmazonEC2Client(pcp);
			amazonEC2Client.setRegion(Region.getRegion(region));
			
			List ls=new ArrayList();
			ls.add("i-0110e52a7c3933282");
			
			
			StopInstancesRequest stopInstance=new StopInstancesRequest();
			stopInstance.setInstanceIds(ls);
			
			StopInstancesResult instancesResult = amazonEC2Client.stopInstances(stopInstance);
			System.out.println(instancesResult.getStoppingInstances());
			/*
			StartInstancesRequest startInstance = new StartInstancesRequest();
			startInstance.setInstanceIds(ls);
			
			StartInstancesResult res  = amazonEC2Client.startInstances(startInstance);
			System.out.println(res.getStartingInstances());
			*/
			
			//RunInstancesRequest ir = new RunInstancesRequest("",1,1);
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

}
