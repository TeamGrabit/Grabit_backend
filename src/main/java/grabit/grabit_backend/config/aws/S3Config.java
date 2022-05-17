package grabit.grabit_backend.config.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("DATABASE")
@Configuration
public class S3Config {

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

	@Bean
	public AmazonS3 amazonS3(){
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(this.region)
				.build();

		return amazonS3;
	}
}
