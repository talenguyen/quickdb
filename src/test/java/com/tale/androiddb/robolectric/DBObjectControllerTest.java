package com.tale.androiddb.robolectric;

import android.app.Activity;
import android.content.Context;

import com.tale.androiddb.core.Column;
import com.tale.androiddb.core.Contract;
import com.tale.androiddb.core.DBControlerFactory;
import com.tale.androiddb.core.DBObjectController;
import com.tale.androiddb.core.SQLiteOpenHelperEx;
import com.tale.androiddb.core.Table;
import com.tale.androiddb.core.Type;
import com.tale.androiddb.robolectric.mock.MockDBObjectHelper;
import com.tale.androiddb.robolectric.mock.Person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by TALE on 7/8/2014.
 */
@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class DBObjectControllerTest {
    private DBObjectController dbObjectController;

    @Before
    public void setUp() {
        Context context = new Activity();
        SQLiteOpenHelperEx sqLiteOpenHelperEx = new SQLiteOpenHelperEx(context, new Contract() {
            @Override
            public List<Table> getTables() {
                return Arrays.asList(Table.with("Person")
                        .column(Column.with("name", Type.TEXT))
                        .column(Column.with("_id", Type.INTEGER).primaryKey(true).autoincrement(true)));
            }

            @Override
            public int getDBVersion() {
                return 1;
            }

            @Override
            public String getDBName() {
                return "test";
            }
        });
        dbObjectController = DBControlerFactory.newDBObjectController(sqLiteOpenHelperEx, new MockDBObjectHelper());
    }

    @Test
    public void testInsertOne() throws Exception {
        long id = insertAMockPerson();
        assertTrue(id > 0);
    }

    @Test
    public void testInsertTwo() throws Exception {
        // Insert one.
        long id = insertAMockPerson();
        assertTrue(id == 1);

        // Insert two
        id = insertAMockPerson();
        assertTrue(id == 2);
    }

    @Test
    public void testUpdateSimpleMethod() throws Exception {
        // Insert a mock item.
        long id = insertAMockPerson();
        assertTrue(id == 1); // Make sure the object has been inserted.

        // Create a mock object to update
        Person person = new Person();
        person._id = id;
        person.name = "Giang Nguyen";

        // Execute update operation.
        int count = dbObjectController.update(person);
        assertTrue(count == 1); // Check one object must be update.

    }

    @Test
    public void testUpdateByQueryOne() throws Exception {
        // Insert a mock item.
        long id = insertAMockPerson();
        assertTrue(id == 1); // Make sure the object has been inserted.

        // Create a mock object to update
        Person person = new Person();
        person.name = "Giang Nguyen";

        // Execute update operation.
        int count = dbObjectController.update(person, "name LIKE ?", new String[]{"Giang"});
        assertTrue(count == 1); // Check one object must be update.
    }

    @Test
    public void testUpdateByQueryTwo() throws Exception {
        // Insert a mock item.
        insertAMockPerson();

        long id = insertAMockPerson();
        assertTrue(id == 2); // Make sure there is 2 objects has been inserted to db.

        // Create a mock object to update
        Person person = mockPerson("Giang Nguyen");

        // Execute update operation.
        int count = dbObjectController.update(person, "name LIKE ?", new String[]{"Giang"});
        assertTrue(count == 2); // Check 2 objects must be update.
    }

    @Test
    public void testOneDelete() throws Exception {

        // Insert a mock item.
        Person person = mockPerson("Giang");
        long id = dbObjectController.insert(person);
        assertTrue(id == 1); // Make sure the object has been inserted.

        // Try to delete the inserted object.
        person._id = id;
        int count = dbObjectController.delete(person);
        assertTrue(count == 1); // Verify 1 object is deleted.
    }

    @Test
    public void testQueryById() throws Exception {
        // Insert a mock object.
        long id = insertAMockPerson();
        assertTrue(id == 1); // Make sure the object has been inserted.

        Object object = dbObjectController.queryById("Person", id);
        assertNotNull(object); // Make sure object not null.

        assertThat(object).isInstanceOf(Person.class); // Make sure that object is instance of Person.

        Person person = (Person) object;
        assertThat(person.name).isEqualTo("Giang");// Make sure the name is "Giang"
    }

    @Test
    public void testQueryByIdTwo() throws Exception {
        // Insert a mock object.
        insertAMockPerson();
        Person person = mockPerson("Giang Nguyen");
        long id = dbObjectController.insert(person);

        assertTrue(id == 2); // Make sure the are 2 objects has been inserted.

        Object object = dbObjectController.queryById("Person", id);
        assertNotNull(object); // Make sure object not null.

        assertThat(object).isInstanceOf(Person.class); // Make sure that object is instance of Person.

        person = (Person) object;
        assertThat(person.name).isEqualTo("Giang Nguyen");// Make sure the name is "Giang"
    }

    @Test
    public void testQueryManyObject() throws Exception {
        // Insert a mock object.
        insertAMockPerson();
        long id = insertAMockPerson();

        assertTrue(id == 2); // Make sure the are 2 objects has been inserted.

        List<Object> objects = dbObjectController.query("Person", "name LIKE ?", new String[]{"Giang"});
        assertNotNull(objects); // Make sure object not null.

        assertThat(objects).hasSize(2);

        for (int i = 0; i < objects.size(); i++) {
            Object object = objects.get(i);
            assertThat(object).isNotNull();
            assertThat(object).isInstanceOf(Person.class);

            Person person = (Person) object;
            assertThat(person.name).isEqualTo("Giang");
        }
    }

    private long insertAMockPerson() {
        Person person = new Person();
        person.name = "Giang";
        return dbObjectController.insert(person);
    }

    private Person mockPerson(String name) {
        Person person = new Person();
        person.name = name;
        return person;
    }

}
