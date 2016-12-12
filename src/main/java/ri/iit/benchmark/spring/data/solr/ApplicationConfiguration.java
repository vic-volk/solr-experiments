package ri.iit.benchmark.spring.data.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import javax.annotation.Resource;

@Configuration
@EnableSolrRepositories
public class ApplicationConfiguration {

    private static final String PROPERTY_NAME_SOLR_SERVER_URL = "solr.host";

    @Resource
    private Environment environment;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient(environment.getRequiredProperty(PROPERTY_NAME_SOLR_SERVER_URL));
    }

    @Bean
    public SolrOperations solrTemplate() {
        return new SolrTemplate(solrClient());
    }
}
