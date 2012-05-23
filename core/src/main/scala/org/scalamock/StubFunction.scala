// Copyright (c) 2011-2012 Paul Butcher
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package org.scalamock

trait StubFunction { self: FakeFunction =>
  
  def handle(arguments: Product): Any = self.factory.handle(new Call(this, arguments)) match {
    case Some(retVal) => retVal
    case None => null
  }
}

class StubFunction0[R](factory: MockFactoryBase, name: Symbol)
  extends FakeFunction0[R](factory, name) with StubFunction {
  
  def when() = factory.add((new CallHandler0[R]).anyNumberOfTimes)
  def when(matcher: ArgumentMatcher0) = factory.add((new CallHandler[R](matcher)).anyNumberOfTimes)
  
  def verify() = factory.add(new CallHandler0[R] with Verify)
  def verify(matcher: ArgumentMatcher0) = factory.add(new CallHandler[R](matcher) with Verify)
}

class StubFunction1[T1, R](factory: MockFactoryBase, name: Symbol)
  extends FakeFunction1[T1, R](factory, name) with StubFunction {

  def when(v1: MockParameter[T1]) = factory.add((new CallHandler1[T1, R](v1)).anyNumberOfTimes)
  def when(matcher: ArgumentMatcher1[T1]) = factory.add((new CallHandler[R](matcher)).anyNumberOfTimes)

  def verify(v1: MockParameter[T1]) = factory.add(new CallHandler1[T1, R](v1) with Verify)
  def verify(matcher: ArgumentMatcher1[T1]) = factory.add(new CallHandler[R](matcher) with Verify)
}

class StubFunction2[T1, T2, R](factory: MockFactoryBase, name: Symbol)
  extends FakeFunction2[T1, T2, R](factory, name) with StubFunction {

  def when(v1: MockParameter[T1], v2: MockParameter[T2]) = factory.add((new CallHandler2[T1, T2, R](v1, v2)).anyNumberOfTimes)
  def when(matcher: ArgumentMatcher2[T1, T2]) = factory.add((new CallHandler[R](matcher)).anyNumberOfTimes)

  def verify(v1: MockParameter[T1], v2: MockParameter[T2]) = factory.add(new CallHandler2[T1, T2, R](v1, v2) with Verify)
  def verify(matcher: ArgumentMatcher2[T1, T2]) = factory.add(new CallHandler[R](matcher) with Verify)
}