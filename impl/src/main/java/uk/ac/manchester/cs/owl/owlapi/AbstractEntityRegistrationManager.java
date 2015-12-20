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
package uk.ac.manchester.cs.owl.owlapi;

import org.semanticweb.owlapi.model.*;

/** Base for entity registration manager. */
public interface AbstractEntityRegistrationManager extends OWLObjectVisitor, SWRLObjectVisitor {

    /**
     * @param ax
     *        axiom whose annotations should be processed
     */
    default void processAxiomAnnotations(OWLAxiom ax) {
        ax.annotations().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLSubClassOfAxiom axiom) {
        axiom.getSubClass().accept(this);
        axiom.getSuperClass().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLNegativeObjectPropertyAssertionAxiom axiom) {
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getObject().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLAsymmetricObjectPropertyAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLReflexiveObjectPropertyAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDisjointClassesAxiom axiom) {
        axiom.classExpressions().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDataPropertyDomainAxiom axiom) {
        axiom.getDomain().accept(this);
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLObjectPropertyDomainAxiom axiom) {
        axiom.getDomain().accept(this);
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLEquivalentObjectPropertiesAxiom axiom) {
        axiom.properties().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLNegativeDataPropertyAssertionAxiom axiom) {
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getObject().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDifferentIndividualsAxiom axiom) {
        axiom.individuals().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDisjointDataPropertiesAxiom axiom) {
        axiom.properties().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDisjointObjectPropertiesAxiom axiom) {
        axiom.properties().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLObjectPropertyRangeAxiom axiom) {
        axiom.getRange().accept(this);
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLObjectPropertyAssertionAxiom axiom) {
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getObject().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLFunctionalObjectPropertyAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLSubObjectPropertyOfAxiom axiom) {
        axiom.getSubProperty().accept(this);
        axiom.getSuperProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDisjointUnionAxiom axiom) {
        axiom.getOWLClass().accept((OWLEntityVisitor) this);
        axiom.classExpressions().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDeclarationAxiom axiom) {
        axiom.getEntity().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLSymmetricObjectPropertyAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDataPropertyRangeAxiom axiom) {
        axiom.getProperty().accept(this);
        axiom.getRange().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLFunctionalDataPropertyAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLEquivalentDataPropertiesAxiom axiom) {
        axiom.properties().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLClassAssertionAxiom axiom) {
        axiom.getClassExpression().accept(this);
        axiom.getIndividual().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLEquivalentClassesAxiom axiom) {
        axiom.classExpressions().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDataPropertyAssertionAxiom axiom) {
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getObject().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLTransitiveObjectPropertyAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLIrreflexiveObjectPropertyAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLSubDataPropertyOfAxiom axiom) {
        axiom.getSubProperty().accept(this);
        axiom.getSuperProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLInverseFunctionalObjectPropertyAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLSameIndividualAxiom axiom) {
        axiom.individuals().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLSubPropertyChainOfAxiom axiom) {
        axiom.getPropertyChain().forEach(a -> a.accept(this));
        axiom.getSuperProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLInverseObjectPropertiesAxiom axiom) {
        axiom.getFirstProperty().accept(this);
        axiom.getSecondProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLHasKeyAxiom axiom) {
        axiom.getClassExpression().accept(this);
        axiom.propertyExpressions().forEach(a -> a.accept(this));
        processAxiomAnnotations(axiom);
    }

    // OWLClassExpressionVisitor
    @Override
    default void visit(OWLObjectIntersectionOf ce) {
        ce.operands().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLObjectUnionOf ce) {
        ce.operands().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLObjectComplementOf ce) {
        ce.getOperand().accept(this);
    }

    @Override
    default void visit(OWLObjectSomeValuesFrom ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLObjectAllValuesFrom ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLObjectHasValue ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLObjectMinCardinality ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLObjectExactCardinality ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLObjectMaxCardinality ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLObjectHasSelf ce) {
        ce.getProperty().accept(this);
    }

    @Override
    default void visit(OWLObjectOneOf ce) {
        ce.individuals().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLDataSomeValuesFrom ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLDataAllValuesFrom ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLDataHasValue ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLDataMinCardinality ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLDataExactCardinality ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    default void visit(OWLDataMaxCardinality ce) {
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    // Data visitor
    @Override
    default void visit(OWLDataComplementOf node) {
        node.getDataRange().accept(this);
    }

    @Override
    default void visit(OWLDataOneOf node) {
        node.values().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLDataIntersectionOf node) {
        node.operands().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLDataUnionOf node) {
        node.operands().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLDatatypeRestriction node) {
        node.getDatatype().accept(this);
        node.facetRestrictions().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLFacetRestriction node) {
        node.getFacetValue().accept(this);
    }

    @Override
    default void visit(OWLLiteral node) {
        node.getDatatype().accept(this);
    }

    // Property expression visitor
    @Override
    default void visit(OWLObjectInverseOf property) {
        property.getInverse().accept(this);
    }

    // Entity visitor
    @Override
    default void visit(OWLAnnotation node) {
        node.getProperty().accept(this);
        node.getValue().accept(this);
        node.annotations().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(OWLAnnotationAssertionAxiom axiom) {
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getValue().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLAnnotationPropertyDomainAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLAnnotationPropertyRangeAxiom axiom) {
        axiom.getProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLSubAnnotationPropertyOfAxiom axiom) {
        axiom.getSubProperty().accept(this);
        axiom.getSuperProperty().accept(this);
        processAxiomAnnotations(axiom);
    }

    @Override
    default void visit(OWLDatatypeDefinitionAxiom axiom) {
        axiom.getDatatype().accept(this);
        axiom.getDataRange().accept(this);
        processAxiomAnnotations(axiom);
    }

    // SWRL Object Visitor
    @Override
    default void visit(SWRLRule rule) {
        rule.body().forEach(a -> a.accept(this));
        rule.head().forEach(a -> a.accept(this));
        processAxiomAnnotations(rule);
    }

    @Override
    default void visit(SWRLClassAtom node) {
        node.getArgument().accept(this);
        node.getPredicate().accept(this);
    }

    @Override
    default void visit(SWRLDataRangeAtom node) {
        node.getArgument().accept(this);
        node.getPredicate().accept(this);
    }

    @Override
    default void visit(SWRLObjectPropertyAtom node) {
        node.getPredicate().accept(this);
        node.getFirstArgument().accept(this);
        node.getSecondArgument().accept(this);
    }

    @Override
    default void visit(SWRLDataPropertyAtom node) {
        node.getPredicate().accept(this);
        node.getFirstArgument().accept(this);
        node.getSecondArgument().accept(this);
    }

    @Override
    default void visit(SWRLBuiltInAtom node) {
        node.allArguments().forEach(a -> a.accept(this));
    }

    @Override
    default void visit(SWRLIndividualArgument node) {
        node.getIndividual().accept(this);
    }

    @Override
    default void visit(SWRLLiteralArgument node) {
        node.getLiteral().accept(this);
    }

    @Override
    default void visit(SWRLDifferentIndividualsAtom node) {
        node.getFirstArgument().accept(this);
        node.getSecondArgument().accept(this);
    }

    @Override
    default void visit(SWRLSameIndividualAtom node) {
        node.getFirstArgument().accept(this);
        node.getSecondArgument().accept(this);
    }
}
