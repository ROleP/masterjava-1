package ru.javaops.masterjava.service.mail.model;

import lombok.*;

/**
 * gkislin
 * 28.10.2016
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
abstract public class MailBaseEntity {

    @Getter
    @Setter
    protected Integer id;

    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailBaseEntity mailBaseEntity = (MailBaseEntity) o;
        return id != null && id.equals(mailBaseEntity.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
