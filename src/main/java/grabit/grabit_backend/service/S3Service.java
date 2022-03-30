package grabit.grabit_backend.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import grabit.grabit_backend.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class S3Service {
	private AmazonS3 amazonS3;
	private ObjectMetadata md;

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region.static}")
	private String region;

	@Value("${cloud.aws.cdn}")
	private String cdn;

	@PostConstruct
	public void setAmazonS3(){
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

		amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(this.region)
				.build();

		md = new ObjectMetadata();
		md.setContentType("image/jpeg");
	}

	public String upload(User user, MultipartFile file) throws IOException {
		String fileName = user.getUserId() + "_" + UUID.randomUUID().toString();

		amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), md)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return cdn+fileName;
	}

}
