package bst;

public class BSTree {

	public String treeName;

	public TreeNode root;

	/**
	 * ����ڵ�
	 * 
	 * @param payload
	 */
	public void addNode(int payload) {
		TreeNode targetNode = new TreeNode();
		targetNode.payload = payload;
		if (root == null) {
			root = targetNode;
			return;
		}
		innerAddNode(root, targetNode);
	}

	/**
	 * ���� ��ӡ
	 */
	public void printSelf() {
		innerPrintSelf(root);
	}

	/**
	 * ����
	 * 
	 * @param payload
	 * @return
	 */
	public TreeNode search(int payload) {
		return innerFind(root, payload);
	}

	public TreeNode max(TreeNode inNode) {
		if (inNode == null)
			return null;
		TreeNode curNode = inNode;
		while (curNode.right != null) {
			curNode = curNode.right;
		}
		return curNode;
	}

	public TreeNode min(TreeNode inNode) {
		if (inNode == null)
			return null;
		TreeNode curNode = inNode;
		while (curNode.left != null) {
			curNode = curNode.left;
		}
		return curNode;
	}

	public TreeNode findPre(int payload) {
		TreeNode treeNode = search(payload);
		if (treeNode == null)
			return null;
		if (treeNode.left != null) {
			return max(treeNode.left);
		}

		TreeNode parentNode = treeNode.parent;
		while (parentNode.left == treeNode) {
			treeNode = parentNode;
			parentNode = parentNode.parent;
		}
		return parentNode;
	}

	public TreeNode findNext(int payload) {
		TreeNode treeNode = search(payload);
		if (treeNode == null)
			return null;
		if (treeNode.right != null) {
			return min(treeNode.right);
		}
		TreeNode parentNode = treeNode.parent;
		while (parentNode.right == treeNode) {
			treeNode = parentNode;
			parentNode = parentNode.parent;
		}
		return parentNode;
	}

	public void delete(int payload) {
		TreeNode treeNode = search(payload);
		if (treeNode == null)
			throw new IllegalArgumentException("payload can not find!");

		if (treeNode.left == null && treeNode.right == null) {
			if (treeNode.parent != null) {
				if (treeNode.parent.left == treeNode) {
					treeNode.parent.left = null;
				} else {
					treeNode.parent.right = null;
				}
			}
		} else if (treeNode.left == null) { // left==null ,right!=null
			if (treeNode.parent != null) {
				if (treeNode.parent.left == treeNode) {
					treeNode.parent.left = treeNode.right;
				} else {
					treeNode.parent.right = treeNode.right;
				}
				treeNode.right.parent=treeNode.parent;
			}
		} else if (treeNode.right == null) {// left!=null ,right==null
			if (treeNode.parent != null) {
				if (treeNode.parent.left == treeNode) {
					treeNode.parent.left = treeNode.left;
				} else {
					treeNode.parent.right = treeNode.left;
				}
				treeNode.left.parent=treeNode.parent;
			}
		} else { // left!=null ,right!=null
			TreeNode nextNode = findNext(payload);
			if(nextNode==treeNode.right){  //��ǰҪɾ��Ľڵ�ĺ�� �� ���Լ��� �ֺ��ӣ���ʱ���Һ��ӵ�����һ��Ϊ��
				if(treeNode.left!=null){
					nextNode.left=treeNode.left;
					treeNode.left.parent=nextNode;					
				}
				
				if (treeNode.parent != null) {
					if (treeNode.parent.left == treeNode) {
						treeNode.parent.left = nextNode;
					} else {
						treeNode.parent.right = nextNode;
					}
					nextNode.parent=treeNode.parent;
				}
			}else{
				//先用 后继 的右孩子 代替 后继位置
				if(nextNode.parent!=null){
					if(nextNode.parent.left==nextNode){
						nextNode.parent.left=nextNode.right;
					}else{
						nextNode.parent.right=nextNode.right;
					}
					if(nextNode.right!=null){
						nextNode.right.parent=nextNode.parent;						
					}
				}
				
				
				//再将 后继 去代替 当前节点
				nextNode.left=treeNode.left;
				nextNode.right=treeNode.right;
				if(nextNode.left!=null){
					nextNode.left.parent=nextNode;
				}
				if(nextNode.right!=null){
					nextNode.right.parent=nextNode;
				}
				
				if(treeNode.parent!=null){
					if(treeNode.parent.left==treeNode){
						treeNode.parent.left=nextNode;
					}else{
						treeNode.parent.right=nextNode;
					}
					nextNode.parent=treeNode.parent;
				}

			}
		}
	}

	private TreeNode innerFind(TreeNode curNode, int payload) {
		if (curNode == null) {
			return null;
		}
		if (curNode.payload == payload) {
			return curNode;
		}
		if (curNode.payload > payload) {
			return innerFind(curNode.left, payload);
		}
		return innerFind(curNode.right, payload);
	}

	private void innerPrintSelf(TreeNode curNode) {
		if (curNode != null) {
			innerPrintSelf(curNode.left);
			System.out.print(curNode.payload + " ");
			innerPrintSelf(curNode.right);
		}
	}

	private void innerAddNode(TreeNode curNode, TreeNode targetNode) {
		if (targetNode.payload < curNode.payload) {
			if (curNode.left == null) {
				curNode.left = targetNode;
				targetNode.parent = curNode;
			} else {
				innerAddNode(curNode.left, targetNode);
			}
		} else {
			if (curNode.right == null) {
				curNode.right = targetNode;
				targetNode.parent = curNode;
			} else {
				innerAddNode(curNode.right, targetNode);
			}
		}
	}
}
