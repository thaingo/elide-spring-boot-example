package example.models;

import com.yahoo.elide.annotation.Include;
import com.yahoo.elide.datastores.aggregation.annotation.Temporal;
import com.yahoo.elide.datastores.aggregation.annotation.TimeGrainDefinition;
import com.yahoo.elide.datastores.aggregation.metadata.enums.TimeGrain;
import com.yahoo.elide.datastores.aggregation.queryengines.sql.annotation.FromTable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

@Include
@FromTable(name = "company")
public class CompanyView {

    @Id
    private String id;

    @Column(name = "id")
    private String companyId;

    private CompanyType type = CompanyType.PRIVATE;

    @Temporal(grains = {
            @TimeGrainDefinition(grain = TimeGrain.MINUTE,
                    expression = "PARSEDATETIME(FORMATDATETIME({{$$column.expr}}, 'yyyy-MM-dd HH:mm'), 'yyyy-MM-dd HH:mm')"),
    }, timeZone = "UTC")
    private Date date;

}
