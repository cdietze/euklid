package pythagoras

import org.junit.runner.RunWith
import org.junit.runners.Suite
import pythagoras.f.AreaTest
import pythagoras.f.MathUtilTest
import pythagoras.f.RectangleTest
import pythagoras.f.VectorTest

@RunWith(Suite::class)
@Suite.SuiteClasses(
        AreaTest::class,
        MathUtilTest::class,
        RectangleTest::class,
        VectorTest::class,

        pythagoras.i.MathUtilTest::class
)
class FullTestSuite
