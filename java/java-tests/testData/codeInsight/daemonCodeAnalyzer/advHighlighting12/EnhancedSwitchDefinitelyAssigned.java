class C {
  void finalVariableAssignedInAllBranches(int k) {
    final String s;
    switch (k) {
      case 1 -> s = "a";
      case 2 -> s = "b";
      case 3, 4 -> s = "c";
      default -> s = "d";
    }
    System.out.println(s);
  }

  enum EnumAB {A, B}
  void finalVariableAssignedInAllEnumConstantBranches(EnumAB ab) {
    final int n;
    switch (ab) {
      case A -> n = 1;
      case B -> n = 2;
    }
    System.out.println(<error descr="Variable 'n' might not have been initialized">n</error>);
  }

  void assignedInSomeBranches(String s) {
    int n;
    switch ((int)Math.random()) {
      case 1 -> n = 1;
      default -> {}
    }
    System.out.println(<error descr="Variable 'n' might not have been initialized">n</error>);
  }

  void finalVariableReassignedAfterSwitchStatement(int n) {
    final String s;
    switch (n) {
      case 1 -> s = "a";
      default -> {}
    }
    <error descr="Variable 's' might already have been assigned to">s</error> = "b";
    System.out.println(s);
  }

  void finalVariableReassignedAfterSwitchExpression(int n) {
    final String s;
    String t = switch (n) {
      case 1 -> s = "a";
      default -> "";
    };
    <error descr="Variable 's' might already have been assigned to">s</error> = t;
    System.out.println(s);
  }

  void finalVariableReassignedInSwitchStatement(int n) {
    final String s = "b";
    switch (n) {
      case 1 -> <error descr="Cannot assign a value to final variable 's'">s</error> = "a";
      default -> {}
    };
    System.out.println(s);
  }

  void finalVariableReassignedInSwitchExpression(int n) {
    final String s = "b";
    String string = switch (n) {
      case 1 -> <error descr="Cannot assign a value to final variable 's'">s</error> = "a";
      default -> "";
    };
    System.out.println(s);
  }


  static class FinalFieldAssignedInSomeBranches {
    <error descr="Variable 'n' might not have been initialized">final int n</error>;
    {
      switch ((int)Math.random()) {
        case 1 -> n = 1;
        default -> {}
      }
    }
  }

  static class FinalFieldAssignedInSomeBranchesNoDefault {
    <error descr="Variable 'n' might not have been initialized">final int n</error>;
    {
      switch ((int)Math.random()) {
        case 1 -> n = 1;
        case 0 -> n = 0;
      }
    }
  }

  static class FinalFieldAssignedInAllBranches {
    final int n;
    {
      switch ((int)Math.random()) {
        case 1 -> n = 1;
        default -> n = 0;
      }
    }
  }

  static class FinalFieldInitializedWithswitchExpression {
    final int n =
      switch ((int)Math.random()) {
        case 1 -> 1;
        default -> 0;
      };
  }
}