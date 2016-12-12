package ri.iit.benchmark.spring.data.solr;

import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ExpBeanRepository extends SolrCrudRepository<SimpleSolrExperimentationBean, Long> {
}
