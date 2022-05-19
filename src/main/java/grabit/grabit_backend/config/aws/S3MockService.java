package grabit.grabit_backend.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.findify.s3mock.S3Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("LOCAL")
@Configuration
public class S3MockService {

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region.static}")
	private String region;

	@Value("${cloud.aws.s3.mock.port}")
	private int port;

	@PostConstruct
	public AmazonS3 amazonS3() {
		AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(getUri(), region);
		AmazonS3 amazonS3 = AmazonS3ClientBuilder
				.standard()
				.withPathStyleAccessEnabled(true)
				.withEndpointConfiguration(endpoint)
				.withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
				.build();
		amazonS3.createBucket(bucket);
		return amazonS3;
	}

	private String getUri() {
		return "http://localhost:" + port;
	}
}
