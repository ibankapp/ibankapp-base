/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.repository.test;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import org.ibankapp.base.persistence.BasePersistenceException;
import org.ibankapp.base.persistence.domain.Page;
import org.ibankapp.base.persistence.domain.Pageable;
import org.ibankapp.base.persistence.domain.Sort;
import org.ibankapp.base.persistence.domain.Specification;
import org.ibankapp.base.persistence.repository.JpaRepository;
import org.ibankapp.base.persistence.validation.test.TestContextConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback(value = false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class JpaRepositoryTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Resource
  private JpaRepository repository;


  @After
  public void clear() {
    repository.deleteAll(SimpleModel.class);
    repository.deleteAll(CompositeModel.class);
  }


  private void persistOne() {

    SimpleModel model = new SimpleModel();
    model.setId("0");
    model.setName("name");

    repository.persist(model);
  }

  private List<SimpleModel> persistTwo() {

    SimpleModel model = new SimpleModel();
    model.setId("0");
    model.setName("name");

    SimpleModel model1 = new SimpleModel();
    model1.setId("1");
    model1.setName("name1");

    List<SimpleModel> models = new ArrayList<SimpleModel>();
    models.add(model);
    models.add(model1);

    return repository.persist(models);
  }

  private void persistThree() {

    SimpleModel model = new SimpleModel();
    model.setId("0");
    model.setName("name");

    SimpleModel model1 = new SimpleModel();
    model1.setId("1");
    model1.setName("name1");

    SimpleModel model2 = new SimpleModel();
    model2.setId("2");
    model2.setName("name2");

    List<SimpleModel> models = new ArrayList<SimpleModel>();
    models.add(model);
    models.add(model1);
    models.add(model2);

    repository.persist(models);
  }

  private void persistNum() {

    for (int i = 0; i < 100; i++) {
      SimpleModel model = new SimpleModel();
      model.setId(((Integer) i).toString());
      model.setName("name" + i);
      repository.persist(model);
    }
  }

  @Test
  public void testPersist() {

    persistOne();

    List<SimpleModel> models = repository.findAll(SimpleModel.class);

    Assert.assertEquals(1, models.size());
    Assert.assertEquals("0", models.get(0).getId());
    Assert.assertEquals("name", models.get(0).getName());

  }

  @Test
  @Rollback
  public void testNullIdPersist() {

    thrown.expect(PersistenceException.class);

    SimpleModel model = new SimpleModel();
    model.setName("name");

    repository.persist(model);
  }

  @Test
  public void testNullPersist() {
    thrown.expect(NullPointerException.class);
    repository.persist((SimpleModel) null);
  }

  @Test
  public void testMerge() {

    persistOne();

    SimpleModel model = new SimpleModel();
    model.setId("0");
    model.setName("name1");
    SimpleModel model1 = repository.merge(model);

    Assert.assertEquals(model.getId(), model1.getId());
    Assert.assertEquals(model.getName(), model1.getName());

    List<SimpleModel> models = repository.findAll(SimpleModel.class);

    Assert.assertEquals(1, models.size());
    Assert.assertEquals("0", models.get(0).getId());
    Assert.assertEquals("name1", models.get(0).getName());

  }

  @Test
  @Rollback
  public void testNullIdMerge() {

    thrown.expect(PersistenceException.class);

    SimpleModel model = new SimpleModel();
    model.setName("name");

    repository.merge(model);
  }

  @Test
  public void testNullMerge() {
    thrown.expect(NullPointerException.class);
    repository.merge((SimpleModel) null);
  }

  @Test
  public void testPersists() {

    List<SimpleModel> models = persistTwo();

    Assert.assertEquals(2, models.size());

  }

  @Test
  public void testNullPersists() {

    List<SimpleModel> models = repository.persist(null);

    Assert.assertNotNull(models);
    Assert.assertEquals(0, models.size());
  }


  @Test
  public void testMerges() {

    persistTwo();

    SimpleModel model = new SimpleModel();
    model.setId("0");
    model.setName("name2");

    SimpleModel model1 = new SimpleModel();
    model1.setId("2");
    model1.setName("name3");

    List<SimpleModel> models = new ArrayList<SimpleModel>();
    models.add(model);
    models.add(model1);

    repository.merge(models);

    List<SimpleModel> models1 = repository.findAll(SimpleModel.class);

    Assert.assertEquals(3, repository.count(SimpleModel.class));
    Assert.assertEquals(3, models1.size());
    Assert.assertEquals("name2", repository.findOne(SimpleModel.class, "0").getName());
    Assert.assertEquals("name1",
        repository.findOne(SimpleModel.class, "1", LockModeType.WRITE).getName());
    Assert.assertEquals("name1", repository.findOne(SimpleModel.class, "1").getName());
    Assert.assertEquals("name3",
        repository.findOne(SimpleModel.class, "2", LockModeType.READ).getName());
    Assert.assertEquals("name3", repository.findOne(SimpleModel.class, "2").getName());

  }

  @Test
  public void testNullMerges() {

    List<SimpleModel> models = repository.merge(null);

    Assert.assertNotNull(models);
    Assert.assertEquals(0, models.size());
  }

  @Test
  public void testExist() {

    persistOne();

    Assert.assertTrue(repository.exist(SimpleModel.class, "0"));
    Assert.assertFalse(repository.exist(SimpleModel.class, "1"));
  }

  @Test
  public void testFindAllSort() {

    persistThree();

    Sort sort = new Sort("name");

    List<SimpleModel> models = repository.findAll(SimpleModel.class, sort);

    Assert.assertEquals("name", models.get(0).getName());
    Assert.assertEquals("name1", models.get(1).getName());
    Assert.assertEquals("name2", models.get(2).getName());

    sort = new Sort(Sort.Direction.DESC, "name");

    models = repository.findAll(SimpleModel.class, sort);

    Assert.assertEquals("name2", models.get(0).getName());
    Assert.assertEquals("name1", models.get(1).getName());
    Assert.assertEquals("name", models.get(2).getName());

  }

  @Test
  public void testFindAllSpec() {
    persistThree();

    Specification<SimpleModel> spec = new NameSpecification("name1");

    List<SimpleModel> models = repository.findAll(SimpleModel.class, spec);

    Assert.assertEquals(1, models.size());
    Assert.assertEquals(1, repository.count(SimpleModel.class, spec));
    Assert.assertEquals("name1", models.get(0).getName());

    spec = new NameDistinctSpecification("name");
    models = repository.findAll(SimpleModel.class, spec);
    Assert.assertEquals(3, models.size());
    Assert.assertEquals(3, repository.count(SimpleModel.class, spec));

    spec = new NullSpecification();
    models = repository.findAll(SimpleModel.class, spec);
    Assert.assertEquals(3, models.size());
    Assert.assertEquals(3, repository.count(SimpleModel.class, spec));

  }

  @Test
  public void testFindAllSpecSort() {
    persistThree();

    Specification<SimpleModel> spec = new NameSpecification("name");

    Sort sort = new Sort("name");

    List<SimpleModel> models = repository.findAll(SimpleModel.class, spec, sort);

    Assert.assertEquals(3, models.size());
    Assert.assertEquals("name", models.get(0).getName());
    Assert.assertEquals("name1", models.get(1).getName());
    Assert.assertEquals("name2", models.get(2).getName());

    sort = new Sort(Sort.Direction.DESC, "name");
    models = repository.findAll(SimpleModel.class, spec, sort);
    Assert.assertEquals(3, models.size());
    Assert.assertEquals("name2", models.get(0).getName());
    Assert.assertEquals("name1", models.get(1).getName());
    Assert.assertEquals("name", models.get(2).getName());

    spec = new NameSpecification("name_");

    models = repository.findAll(SimpleModel.class, spec, sort);
    Assert.assertEquals(2, models.size());
    Assert.assertEquals("name2", models.get(0).getName());
    Assert.assertEquals("name1", models.get(1).getName());
  }

  @Test
  public void testFindAllFull() {
    persistThree();

    List<SimpleModel> models = repository
        .findAll(SimpleModel.class, null, null, LockModeType.PESSIMISTIC_WRITE);

    Assert.assertEquals(3, models.size());

    models = repository.findAll(SimpleModel.class, null, null, LockModeType.PESSIMISTIC_WRITE);

    Assert.assertEquals(3, models.size());
  }

  @Test
  public void testFindAllIds() {
    persistThree();

    List<SimpleModel> models = repository.findAll(SimpleModel.class, null, false);
    Assert.assertEquals(0, models.size());

    List<String> ids = new ArrayList<String>();

    models = repository.findAll(SimpleModel.class, ids, false);
    Assert.assertEquals(0, models.size());

    ids.add("1");
    ids.add("2");

    models = repository.findAll(SimpleModel.class, ids, false);
    Assert.assertEquals(2, models.size());

    models = repository.findAll(SimpleModel.class, ids, true);
    Assert.assertEquals(2, models.size());

    ids = new ArrayList<String>();

    ids.add("1");
    ids.add("4");

    models = repository.findAll(SimpleModel.class, ids, true);
    Assert.assertEquals(1, models.size());

    models = repository.findAll(SimpleModel.class, ids, false);
    Assert.assertEquals(1, models.size());

    ids = new ArrayList<String>();

    ids.add("3");
    ids.add("4");

    models = repository.findAll(SimpleModel.class, ids, true);
    Assert.assertEquals(0, models.size());

    models = repository.findAll(SimpleModel.class, ids, false);
    Assert.assertEquals(0, models.size());

  }

  private void persistCompisiteModelThree() {

    CompositeId id1 = new CompositeId("0", "0");
    CompositeId id2 = new CompositeId("0", "1");

    CompositeModel model = new CompositeModel();
    model.setId(id1);
    model.setName("name");

    CompositeModel model1 = new CompositeModel();
    model1.setId(id2);
    model1.setName("name1");

    CompositeId id3 = new CompositeId("0", "2");

    CompositeModel model2 = new CompositeModel();
    model2.setId(id3);
    model2.setName("name2");

    List<CompositeModel> models = new ArrayList<CompositeModel>();
    models.add(model);
    models.add(model1);
    models.add(model2);

    repository.persist(models);
  }

  @Test
  public void testFindAllCompositeIds() {

    persistCompisiteModelThree();

    CompositeId id1 = new CompositeId("0", "0");
    CompositeId id2 = new CompositeId("0", "1");

    List<CompositeId> ids = new ArrayList<CompositeId>();
    ids.add(id1);
    ids.add(id2);

    List<CompositeModel> models = repository.findAll(CompositeModel.class, ids, false);
    Assert.assertEquals(2, models.size());

    models = repository.findAll(CompositeModel.class, ids, true);
    Assert.assertEquals(2, models.size());

    id1 = new CompositeId("0", "0");
    id2 = new CompositeId("1", "1");

    ids = new ArrayList<CompositeId>();
    ids.add(id1);
    ids.add(id2);

    models = repository.findAll(CompositeModel.class, ids, true);
    Assert.assertEquals(1, models.size());

    models = repository.findAll(CompositeModel.class, ids, false);
    Assert.assertEquals(1, models.size());
  }

  @Test
  public void testDelete() {
    persistThree();

    repository.delete(SimpleModel.class, "0");

    List<SimpleModel> simpleModels = repository.findAll(SimpleModel.class);
    Assert.assertEquals(2, simpleModels.size());

    persistCompisiteModelThree();

    CompositeId id = new CompositeId("0", "0");

    repository.delete(CompositeModel.class, id);

    List<CompositeModel> compositeModels = repository.findAll(CompositeModel.class);
    Assert.assertEquals(2, compositeModels.size());

  }

  @Test
  public void testDeleteNull() {

    thrown.expect(NullPointerException.class);

    persistThree();

    repository.delete(SimpleModel.class, "3");
  }

  @Test
  public void testDeleteMerge() {

    SimpleModel model = new SimpleModel();
    model.setId("4");
    model.setName("name4");

    repository.delete(model);
  }

  @Test
  public void testDeletes() {
    persistThree();

    List<String> ids = new ArrayList<String>();

    ids.add("0");
    ids.add("1");

    List<SimpleModel> models = repository.findAll(SimpleModel.class, ids, true);
    repository.delete(models);

    models = repository.findAll(SimpleModel.class);
    Assert.assertEquals(1, models.size());
    Assert.assertEquals("2", models.get(0).getId());

  }

  @Test
  public void testFindAllPage() {
    persistNum();

    Pageable pageable = new Pageable(0, 15);

    Page<SimpleModel> page = repository.findAll(SimpleModel.class, pageable);

    Assert.assertEquals(15, page.getPageSize());
    Assert.assertEquals(15, page.getItems().size());
    Assert.assertEquals(100, page.getTotalCount());
    Assert.assertEquals(0, page.getCurrentIndex());

    Specification<SimpleModel> spec = new NameSpecification("name1");
    pageable = new Pageable(0, 5);

    page = repository.findAll(SimpleModel.class, spec, pageable);
    Assert.assertEquals(5, page.getPageSize());
    Assert.assertEquals(5, page.getItems().size());
    Assert.assertEquals(11, page.getTotalCount());

    pageable = new Pageable(3, 5);

    page = repository.findAll(SimpleModel.class, spec, pageable);
    Assert.assertEquals(5, page.getPageSize());
    Assert.assertEquals(0, page.getItems().size());
    Assert.assertEquals(11, page.getTotalCount());

    pageable = new Pageable(0, 5);
    Sort sort = new Sort("name");

    page = repository.findAll(SimpleModel.class, spec, sort, pageable);
    Assert.assertEquals(5, page.getPageSize());
    Assert.assertEquals(5, page.getItems().size());
    Assert.assertEquals(11, page.getTotalCount());

    Assert.assertEquals("name1", page.getItems().get(0).getName());
    Assert.assertEquals("name10", page.getItems().get(1).getName());
    Assert.assertEquals("name11", page.getItems().get(2).getName());

    page.setCurrentIndex(-1);
    Assert.assertEquals(0, page.getCurrentIndex());

    new Page<SimpleModel>(page.getItems(), page.getTotalCount());
    new Page<SimpleModel>(page.getItems(), page.getTotalCount(), 0);

    page.setTotalCount(-1);

    Assert.assertEquals(0, page.getTotalCount());

    pageable.setPage(0);
    pageable.setSize(10);

    Assert.assertEquals(0, pageable.getPage());
    Assert.assertEquals(10, pageable.getSize());

    page = repository.findAll(SimpleModel.class, spec, (Pageable) null);
    Assert.assertEquals(11, page.getItems().size());
  }

  @Test
  public void testCountError() {

    thrown.expect(BasePersistenceException.class);
    thrown.expectMessage("jpql语句不能为空");

    repository.count(" ");
  }

  @Test
  public void testCountError1() {

    thrown.expect(BasePersistenceException.class);
    thrown.expectMessage("jpql语句不能为空");

    repository.count(" order by");
  }

  @Test
  public void testCountError2() {

    thrown.expect(BasePersistenceException.class);
    thrown.expectMessage("必须包含关键字from");

    repository.count(" SimpleModel order by");
  }

  @Test
  public void testFindSpecSingle() {
    persistThree();

    Specification<SimpleModel> spec = new NameSpecification("name1");

    SimpleModel model = repository.findOne(spec, SimpleModel.class);

    Assert.assertEquals("1", model.getId());
    Assert.assertEquals("name1", model.getName());

    model = repository.findOne(spec, SimpleModel.class, LockModeType.WRITE);

    Assert.assertEquals("1", model.getId());
    Assert.assertEquals("name1", model.getName());
  }

  @Test
  public void testFindJpqlSingleLock() {
    persistThree();

    SimpleModel model = repository
        .findOne("from SimpleModel where name = 'name1'", SimpleModel.class,
            LockModeType.PESSIMISTIC_WRITE);

    Assert.assertEquals("1", model.getId());
    Assert.assertEquals("name1", model.getName());
  }

  @Test
  public void testFindJpqlSingle() {
    persistThree();

    SimpleModel model = repository
        .findOne("from SimpleModel where name = 'name1'", SimpleModel.class);

    Assert.assertEquals("1", model.getId());
    Assert.assertEquals("name1", model.getName());
  }

  @Test
  public void testFindJpqlSingleError() {

    thrown.expect(NonUniqueResultException.class);

    persistThree();

    repository.findOne("from SimpleModel where name like 'name%'", SimpleModel.class);
  }

  @Test
  public void testFindJpqlSingleError1() {

    thrown.expect(NoResultException.class);

    persistThree();

    repository.findOne("from SimpleModel where name = 'name%'", SimpleModel.class);

  }

  @Test
  public void testFindAllJpql() {
    persistNum();

    List<SimpleModel> simpleModels = repository
        .findAll(SimpleModel.class, "from SimpleModel");

    Assert.assertEquals(100, simpleModels.size());

    simpleModels = repository
        .findAll(SimpleModel.class, "from SimpleModel where name like 'name1%'");

    Assert.assertEquals(11, simpleModels.size());
  }

  @Test
  public void testFindAllJpqlPage() {
    persistNum();

    Pageable pageable = new Pageable(0, 15);

    Page<SimpleModel> page = repository
        .findAll(SimpleModel.class, "from SimpleModel", pageable);

    Assert.assertEquals(15, page.getPageSize());
    Assert.assertEquals(15, page.getItems().size());
    Assert.assertEquals(100, page.getTotalCount());
    Assert.assertEquals(0, page.getCurrentIndex());

    pageable = new Pageable(0, 5);

    page = repository
        .findAll(SimpleModel.class, "from SimpleModel where name like 'name1%'", pageable);

    Assert.assertEquals(5, page.getPageSize());
    Assert.assertEquals(5, page.getItems().size());
    Assert.assertEquals(11, page.getTotalCount());

    pageable = new Pageable(3, 5);

    page = repository
        .findAll(SimpleModel.class, "from SimpleModel where name like 'name1%'", pageable);

    Assert.assertEquals(5, page.getPageSize());
    Assert.assertEquals(0, page.getItems().size());
    Assert.assertEquals(11, page.getTotalCount());

    pageable = new Pageable(0, 5);

    page = repository
        .findAll(SimpleModel.class, "from SimpleModel where name like 'name1%' order by name",
            pageable);

    Assert.assertEquals(5, page.getPageSize());
    Assert.assertEquals(5, page.getItems().size());
    Assert.assertEquals(11, page.getTotalCount());

    Assert.assertEquals("name1", page.getItems().get(0).getName());
    Assert.assertEquals("name10", page.getItems().get(1).getName());
    Assert.assertEquals("name11", page.getItems().get(2).getName());
  }


}
