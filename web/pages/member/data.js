export const options = [
  {
    departmentName: '一标段',
    departmentId: '1',
    children: [
      {
        departmentName: '一工区',
        departmentId: '11',
        children: [
          {
            departmentName: '实发井',
            departmentId: '111',
            children: [
              {
                departmentName: '开挖支护',
                departmentId: '1111',
              },
              {
                departmentName: '文明施工',
                departmentId: '1112',
              }
            ],
          },
          {
            departmentName: '富锦路',
            departmentId: '112',
            children: [],
          }
        ],
      },
      {
        departmentName: '二工区',
        departmentId: '12',
        children: [],
      },
      {
        departmentName: '三工区',
        departmentId: '13',
        children: [],
      }
    ],
  },
];