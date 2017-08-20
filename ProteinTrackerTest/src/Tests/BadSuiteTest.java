package Tests;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@ExcludeCategory(GoodTestsCategory.class)
@IncludeCategory(BadCategory.class)
@Suite.SuiteClasses({ ProteinTrackerSecondTest.class, ProteinTrackerFirstTest.class })
public class BadSuiteTest {

}
