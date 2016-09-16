package cn.sheep3.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by sheep3 on 16-9-14.
 */
@ToString
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity implements Persistable<Long> {

    @Getter
    @Setter
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_created_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "c_last_modified_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Getter
    @Setter
    @Version
    @Column(name = "c_version")
    private Long version;

    @Override
    public boolean isNew() {
        return null == getId();
    }

    public DateTime getCreatedDate() {
        return null == createdDate ? null : new DateTime(createdDate);
    }

    public void setCreatedDate(final DateTime createdDate) {
        this.createdDate = null == createdDate ? null : createdDate.toDate();
    }

    public DateTime getLastModifiedDate() {
        return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
    }

    public void setLastModifiedDate(final DateTime lastModifiedDate) {
        this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
    }
}
