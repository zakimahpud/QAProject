package Tests;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@IncludeCategory(GoodTestsCategory.class)
@ExcludeCategory({ EndCaseTestsCategory.class, BadCategory.class })

@Suite.SuiteClasses({ ProteinTrackerFirstTest.class, ProteinTrackerSecondTest.class })
public class GoodSuiteTests {

}
