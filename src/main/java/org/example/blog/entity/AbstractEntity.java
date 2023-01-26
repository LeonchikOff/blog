package org.example.blog.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity<CertainTypeOfObligatoryFieldIdWithinThisClass>
        implements Serializable {

    private CertainTypeOfObligatoryFieldIdWithinThisClass id;

    public CertainTypeOfObligatoryFieldIdWithinThisClass getId() {
        return id;
    }

    public void setId(CertainTypeOfObligatoryFieldIdWithinThisClass id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
