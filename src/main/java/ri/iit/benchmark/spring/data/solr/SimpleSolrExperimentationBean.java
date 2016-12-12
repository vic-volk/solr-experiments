package ri.iit.benchmark.spring.data.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class SimpleSolrExperimentationBean {

    @Id
    private String id;

    @Field("some_text_t")
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
