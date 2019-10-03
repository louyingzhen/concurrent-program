package example;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class UnsafeCachingFactorizer implements Servlet {

  private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();

  private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();

  public void service(ServletRequest req, ServletResponse resp)
      throws ServletException, IOException {
    BigInteger i = extractFromRequest(req);
    if (i.equals(lastNumber.get())) {
      encodeIntoResponse(resp, lastFactors.get());
    } else {
      BigInteger[] factors = factor(i);
      lastNumber.set(i);
      lastFactors.set(factors);
      encodeIntoResponse(resp, factors);
    }
  }

  void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {}

  BigInteger extractFromRequest(ServletRequest req) {
    return new BigInteger("7");
  }

  BigInteger[] factor(BigInteger i) {
    // Doesn't really factor
    return new BigInteger[] {i};
  }

  public void init(ServletConfig servletConfig) throws ServletException {}

  public ServletConfig getServletConfig() {
    return null;
  }

  public String getServletInfo() {
    return null;
  }

  public void destroy() {}
}
