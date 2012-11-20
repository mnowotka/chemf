/**                                                      **\
**   Copyright (c) 2012 Organic Chemistry Group          **
**                 Zurich University of Applied Sciences **
**                 Wädenswil, Switzerland                **
\**                                                      **/

import chemf.graph.{Edge, LGraph}
import scalaz._, Scalaz._

/**
 * @author Stefan Höck
 */
package object chemf {

  type Formula = Map[Isotope,Int]

  type Molecule = LGraph[Bond,Atom]

  type ValNel[+E,+A] = Validation[NonEmptyList[E],A]

  type ValRes[+A] = ValNel[String,A]

  /**
   * Adjust all error messages (if any) in v by applying function f.
   */
  def mapErr[E,F,A](v: ValNel[E,A], f: E ⇒ F): ValNel[F,A] =
    v.fail ∘∘ f validation

  /**
   * Adjust all error messages (if any) in v by applying function f.
   * This is the recommended version of mapErr when using Strings as
   * error messages. Helps with type inference.
   */
  def mapErrS[A](v: ValRes[A], f: String ⇒ String): ValRes[A] =
    mapErr(v, f)

}

// vim: set ts=2 sw=2 et:
