package twitterTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import twitterTests.category.ApiTest;
import twitterTests.tests.apiTests.TwitterApiTests;
import twitterTests.tests.uiTests.TwitterUiTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({TwitterApiTests.class, TwitterUiTests.class})
public class TestSuite {

}
