/*
 * This file is generated by jOOQ.
 */
package ca.softwarespace.qiyanna.dataaggregator.models.generated.tables;


import ca.softwarespace.qiyanna.dataaggregator.models.generated.Indexes;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.Keys;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.Qiyanna;
import ca.softwarespace.qiyanna.dataaggregator.models.generated.tables.records.TierRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Tier extends TableImpl<TierRecord> {

    private static final long serialVersionUID = -1901900077;

    /**
     * The reference instance of <code>qiyanna.tier</code>
     */
    public static final Tier TIER = new Tier();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TierRecord> getRecordType() {
        return TierRecord.class;
    }

    /**
     * The column <code>qiyanna.tier.tierid</code>.
     */
    public final TableField<TierRecord, Integer> TIERID = createField("tierid", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('tier_tierid_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>qiyanna.tier.shortname</code>.
     */
    public final TableField<TierRecord, String> SHORTNAME = createField("shortname", org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * Create a <code>qiyanna.tier</code> table reference
     */
    public Tier() {
        this(DSL.name("tier"), null);
    }

    /**
     * Create an aliased <code>qiyanna.tier</code> table reference
     */
    public Tier(String alias) {
        this(DSL.name(alias), TIER);
    }

    /**
     * Create an aliased <code>qiyanna.tier</code> table reference
     */
    public Tier(Name alias) {
        this(alias, TIER);
    }

    private Tier(Name alias, Table<TierRecord> aliased) {
        this(alias, aliased, null);
    }

    private Tier(Name alias, Table<TierRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Tier(Table<O> child, ForeignKey<O, TierRecord> key) {
        super(child, key, TIER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Qiyanna.QIYANNA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.TIER_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TierRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TIER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TierRecord> getPrimaryKey() {
        return Keys.TIER_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TierRecord>> getKeys() {
        return Arrays.<UniqueKey<TierRecord>>asList(Keys.TIER_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tier as(String alias) {
        return new Tier(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tier as(Name alias) {
        return new Tier(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Tier rename(String name) {
        return new Tier(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Tier rename(Name name) {
        return new Tier(name, null);
    }
}