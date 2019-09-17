/*
 * This file is generated by jOOQ.
 */
package ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records;


import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.Role;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleRecord extends UpdatableRecordImpl<RoleRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = -2052275171;

    /**
     * Setter for <code>qiyanna.role.roleid</code>.
     */
    public void setRoleid(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>qiyanna.role.roleid</code>.
     */
    public Integer getRoleid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>qiyanna.role.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>qiyanna.role.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Role.ROLE.ROLEID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Role.ROLE.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getRoleid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getRoleid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleRecord value1(Integer value) {
        setRoleid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RoleRecord
     */
    public RoleRecord() {
        super(Role.ROLE);
    }

    /**
     * Create a detached, initialised RoleRecord
     */
    public RoleRecord(Integer roleid, String name) {
        super(Role.ROLE);

        set(0, roleid);
        set(1, name);
    }
}