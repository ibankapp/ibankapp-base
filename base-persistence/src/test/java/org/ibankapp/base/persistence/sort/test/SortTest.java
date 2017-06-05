package org.ibankapp.base.persistence.sort.test;

import org.ibankapp.base.persistence.domain.Sort;
import org.ibankapp.base.persistence.util.QueryUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class SortTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNewSortWithOrders() {

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name");
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "type");
        Sort sort = new Sort(order, order1);

        Sort.Order order2 = sort.getOrderFor("name");
        Assert.assertEquals(order2, order);
    }

    @Test
    public void testNullSort() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("您需要提供至少一个属性进行排序");

        new Sort((List<Sort.Order>) null);
    }

    @Test
    public void testEmptySort() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("您需要提供至少一个属性进行排序");

        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        new Sort(orders);
    }

    @Test
    public void testNullSort1() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("You have to provide at least one property to sort by");

        new Sort(Sort.Direction.ASC, (List<String>) null);
    }

    @Test
    public void testNullSort2() {

        thrown.expect(NullPointerException.class);
//        thrown.expectMessage("You have to provide at least one property to sort by");

        new Sort(Sort.Direction.ASC, (String[]) null);
    }

    @Test
    public void testEmptySort1() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("You have to provide at least one property to sort by");

        new Sort(Sort.Direction.ASC, new ArrayList<String>());
    }

    @Test
    public void testAndSort() {
        Sort sort = new Sort("name");
        Sort sort1 = new Sort("type");

        Sort sort2 = sort.and(sort1);

        Assert.assertEquals("name: ASC,type: ASC", sort2.toString());

        Assert.assertNull(sort2.getOrderFor("id"));
    }

    @Test
    public void testNullAndSort() {
        Sort sort = new Sort("name");

        Sort sort2 = sort.and(null);

        Assert.assertEquals("name: ASC", sort2.toString());
    }

    @Test
    public void testEquals() {

        Sort sort1 = new Sort("name");

        Sort sort3 = new Sort(Sort.Direction.ASC, "name");
        Sort sort4 = new Sort(Sort.Direction.DESC, "name");

        Object o = new Object();

        Assert.assertTrue(sort1.equals(sort1));
        Assert.assertFalse(sort1.equals(o));
        Assert.assertTrue(sort1.equals(sort3));
        Assert.assertFalse(sort1.equals(sort4));

    }

    @Test
    public void testHashcode() {

        Sort sort = new Sort("name");

        Sort sort1 = new Sort(Sort.Direction.ASC, "name");

        Sort sort2 = new Sort(Sort.Direction.DESC, "name");

        Assert.assertEquals(sort.hashCode(), sort1.hashCode());
        Assert.assertNotEquals(sort.hashCode(), sort2.hashCode());
    }

    @Test
    public void testDirection() {

        Sort.Direction dir = Sort.Direction.fromString("ASC");
        Assert.assertEquals(Sort.Direction.ASC, dir);
        dir = Sort.Direction.fromString("DESC");

        Assert.assertEquals(Sort.Direction.DESC, dir);
    }

    @Test
    public void testDirectionError() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid value 'MED' for orders given! Has to be either 'desc' or 'asc'");

        Sort.Direction.fromString("MED");
    }

    @Test
    public void testDirectionNull() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid value 'null' for orders given! Has to be either 'desc' or 'asc'");

        Sort.Direction.fromString(null);
    }

    @Test
    public void testDirectionFromStringOrNull() {

        Sort.Direction dir = Sort.Direction.fromStringOrNull(null);
        Assert.assertNull(dir);

        dir = Sort.Direction.fromStringOrNull("DESC");
        Assert.assertEquals(dir, Sort.Direction.DESC);
    }

    @Test
    public void testNewOrder() {

        Sort.Order order = new Sort.Order("name");

        Assert.assertEquals("name", order.getProperty());
        Assert.assertEquals(Sort.Direction.ASC, order.getDirection());
    }

    @Test
    public void testNewOrderNullProperty() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Property must not null or empty");

        new Sort.Order(null);
    }

    @Test
    public void testNewOrderEmptyProperty() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Property must not null or empty");

        new Sort.Order("");
    }

    @Test
    public void testNewOrderNullDir() {

        Sort.Order order = new Sort.Order(null, "name");

        Assert.assertEquals("name", order.getProperty());
        Assert.assertEquals(Sort.Direction.ASC, order.getDirection());
    }

    @Test
    public void testNewOrderWith() {

        Sort.Order order = new Sort.Order("name");
        order = order.with(Sort.Direction.DESC);

        Assert.assertEquals("name", order.getProperty());
        Assert.assertEquals(Sort.Direction.DESC, order.getDirection());
    }

    @Test
    public void testNewOrderWithProperties() {

        Sort.Order order = new Sort.Order("name");
        Sort sort = order.withProperties("id", "type");

        Assert.assertEquals(Sort.Direction.ASC, sort.getOrderFor("id").getDirection());
        Assert.assertEquals(Sort.Direction.ASC, sort.getOrderFor("type").getDirection());
    }

//    @Test
//    public void testIgnoreCase() {
//        Sort.Order order = new Sort.Order("name");
//        order = order.ignoreCase();
//
//        Assert.assertEquals("name: ASC, ignoring case", order.toString());
//
//        Sort.Order order1 = new Sort.Order("name");
//
//        Assert.assertFalse(order.equals(order1));
//        Assert.assertNotEquals(order.hashCode(), order1.hashCode());
//
//        Object o = new Object();
//        Assert.assertFalse(order.equals(o));
//    }

    @Test
    public void testOrderEqual() {
        Sort.Order order = new Sort.Order("name");
        Sort.Order order1 = new Sort.Order("name");
//        order1 = order1.ignoreCase();
//        Assert.assertFalse(order.equals(order1));

//        order = order.ignoreCase();

        Object o = new Object();
        Assert.assertFalse(order.equals(o));

        Assert.assertTrue(order.equals(order1));

        order = order.with(Sort.Direction.DESC);
        Assert.assertFalse(order.equals(order1));

        order = new Sort.Order(Sort.Direction.DESC, "name1");
        Assert.assertFalse(order.equals(order1));

        order = new Sort.Order(Sort.Direction.ASC, "name1");
        Assert.assertFalse(order.equals(order1));
    }

    @Test
    public void testToOrders() {

        new QueryUtils();

        List<javax.persistence.criteria.Order> orders = QueryUtils.toOrders(null,null,null);

        Assert.assertEquals(0,orders.size());

    }

}
