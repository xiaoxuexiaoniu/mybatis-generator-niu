/**
 * Copyright 2006-2016 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mybatis.generator.codegen.ibatis2.dao.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Jeff Butler
 *
 */
public class DeleteByExampleMethodGenerator extends AbstractDAOElementGenerator {

    public DeleteByExampleMethodGenerator() {
        super();
    }

    @Override
    public void addImplementationElements(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = getMethodShell(importedTypes);

        StringBuilder sb = new StringBuilder();
        sb.append("int rows = "); //$NON-NLS-1$
        sb.append(daoTemplate.getDeleteMethod(introspectedTable
                .getIbatis2SqlMapNamespace(), introspectedTable
                .getDeleteByExampleStatementId(), "example")); //$NON-NLS-1$
        method.addBodyLine(sb.toString());
        method.addBodyLine("return rows;"); //$NON-NLS-1$

        if (context.getPlugins().clientDeleteByExampleMethodGenerated(
                method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        if (getExampleMethodVisibility() == JavaVisibility.PUBLIC) {
            Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
            Method method = getMethodShell(importedTypes);

            if (context.getPlugins().clientDeleteByExampleMethodGenerated(
                    method, interfaze, introspectedTable)) {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }
        }
    }

    private Method getMethodShell(Set<FullyQualifiedJavaType> importedTypes) {
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getExampleType());
        importedTypes.add(type);

        Method method = new Method();
        method.setVisibility(getExampleMethodVisibility());
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(getDAOMethodNameCalculator()
                .getDeleteByExampleMethodName(introspectedTable));
        method.addParameter(new Parameter(type, "example")); //$NON-NLS-1$

        for (FullyQualifiedJavaType fqjt : daoTemplate.getCheckedExceptions()) {
            method.addException(fqjt);
            importedTypes.add(fqjt);
        }

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        return method;
    }
}
