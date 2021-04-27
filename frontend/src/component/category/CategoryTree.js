import React from 'react';
import CategorySubTree from "./CategorySubTree";

export default function CategoryTree(props) {

  return (
      <div>
        {props.categories
        .filter(category => category.parentId === null)
        .map((category) =>
            <CategorySubTree
                key={category.domainId}
                parentId={category.domainId}
                parentName={category.name}
                allCategories={props.categories}
                onCategorySelect={props.onCategorySelect}
                layer={1}
            />
        )}
      </div>
  );
}
