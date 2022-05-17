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
import org.springframework.context.annotation.Profile;
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
	private ObjectMetadata md = new ObjectMetadata();

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.cdn}")
	private String cdn;

	public S3Service(AmazonS3 amazonS3) {
		this.amazonS3 = amazonS3;
	}

	public String upload(User user, MultipartFile file) throws IOException {
		String fileName = user.getUserId() + "_" + UUID.randomUUID().toString();
		md.setContentType("image/jpeg");

		amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), md)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		return cdn+fileName;
	}

}
