/* This file is part of the OWL API.
 * The contents of this file are subject to the LGPL License, Version 3.0.
 * Copyright 2014, The University of Manchester
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0 in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License. */
package org.semanticweb.owlapi.util;

import javax.annotation.Nonnull;

import org.semanticweb.owlapi.model.*;

/** Returns true if the visited axioms are an ontology in Horn-SHIQ form. */
public class HornAxiomVisitorEx implements OWLAxiomVisitorEx<Boolean> {

    @Nonnull private final PositiveAppearanceVisitorEx positive = new PositiveAppearanceVisitorEx();
    @Nonnull private final NegativeAppearanceVisitorEx negative = new NegativeAppearanceVisitorEx();

    protected Boolean checkNegative(OWLClassExpression c) {
        return Boolean.valueOf(c.accept(negative).booleanValue());
    }

    protected Boolean checkPositive(OWLClassExpression c) {
        return Boolean.valueOf(c.accept(positive).booleanValue());
    }

    @Override
    public Boolean doDefault(Object object) {
        return Boolean.FALSE;
    }

    @Override
    public Boolean visit(OWLSubAnnotationPropertyOfAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLAnnotationPropertyDomainAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLAnnotationPropertyRangeAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(@Nonnull OWLSubClassOfAxiom axiom) {
        return checkNegative(axiom.getSubClass()) && checkNegative(axiom.getSuperClass());
    }

    @Override
    public Boolean visit(OWLDisjointClassesAxiom axiom) {
        return !axiom.classExpressions().anyMatch(c -> !checkNegative(c));
    }

    @Override
    public Boolean visit(OWLObjectPropertyDomainAxiom axiom) {
        return checkPositive(axiom.getDomain());
    }

    @Override
    public Boolean visit(OWLEquivalentObjectPropertiesAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLObjectPropertyRangeAxiom axiom) {
        return checkPositive(axiom.getRange());
    }

    @Override
    public Boolean visit(OWLFunctionalObjectPropertyAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLSubObjectPropertyOfAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLDisjointUnionAxiom axiom) {
        if (neitherPositiveNorNegative(axiom.getOWLClass()).booleanValue()) {
            return Boolean.FALSE;
        }
        return Boolean.valueOf(!axiom.classExpressions().anyMatch(this::neitherPositiveNorNegative));
    }

    protected Boolean neitherPositiveNorNegative(OWLClassExpression c1) {
        return Boolean.valueOf(!checkPositive(c1).booleanValue() || !checkNegative(c1).booleanValue());
    }

    @Override
    public Boolean visit(OWLDeclarationAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLAnnotationAssertionAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLSymmetricObjectPropertyAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLEquivalentClassesAxiom axiom) {
        return Boolean.valueOf(!axiom.classExpressions().anyMatch(this::neitherPositiveNorNegative));
    }

    @Override
    public Boolean visit(OWLTransitiveObjectPropertyAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLInverseFunctionalObjectPropertyAxiom axiom) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OWLInverseObjectPropertiesAxiom axiom) {
        return Boolean.TRUE;
    }

    private class PositiveAppearanceVisitorEx implements OWLClassExpressionVisitorEx<Boolean> {

        PositiveAppearanceVisitorEx() {}

        @Override
        public Boolean doDefault(Object object) {
            return Boolean.FALSE;
        }

        @Override
        public Boolean visit(OWLClass ce) {
            return Boolean.TRUE;
        }

        @Override
        public Boolean visit(OWLObjectIntersectionOf ce) {
            return !ce.operands().anyMatch(c -> !c.accept(this));
        }

        @Override
        public Boolean visit(OWLObjectComplementOf ce) {
            return checkNegative(ce.getOperand());
        }

        @Override
        public Boolean visit(OWLObjectSomeValuesFrom ce) {
            return ce.getFiller().accept(this);
        }

        @Override
        public Boolean visit(OWLObjectAllValuesFrom ce) {
            return ce.getFiller().accept(this);
        }

        @Override
        public Boolean visit(OWLObjectMinCardinality ce) {
            return ce.getFiller().accept(this);
        }

        @Override
        public Boolean visit(OWLObjectExactCardinality ce) {
            return ce.getCardinality() <= 1 && ce.getFiller().accept(this).booleanValue()
                && checkNegative(ce.getFiller());
        }

        @Override
        public Boolean visit(OWLObjectMaxCardinality ce) {
            return ce.getCardinality() <= 1 && checkNegative(ce.getFiller());
        }
    }

    private class NegativeAppearanceVisitorEx implements OWLClassExpressionVisitorEx<Boolean> {

        NegativeAppearanceVisitorEx() {}

        @Override
        public Boolean doDefault(Object object) {
            return Boolean.FALSE;
        }

        @Override
        public Boolean visit(OWLClass ce) {
            return Boolean.TRUE;
        }

        @Override
        public Boolean visit(OWLObjectIntersectionOf ce) {
            return !ce.operands().anyMatch(c -> !c.accept(this));
        }

        @Override
        public Boolean visit(OWLObjectUnionOf ce) {
            return !ce.operands().anyMatch(c -> !c.accept(this));
        }

        @Override
        public Boolean visit(OWLObjectSomeValuesFrom ce) {
            return ce.getFiller().accept(this);
        }

        @Override
        public Boolean visit(OWLObjectMinCardinality ce) {
            return ce.getCardinality() <= 1 && ce.getFiller().accept(this).booleanValue();
        }
    }
}
